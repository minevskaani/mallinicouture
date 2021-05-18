#include <cstdlib>
#include <cstdio>
#include <cstring>
#include <csignal>

#include <unistd.h>

#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h> // inet_ntoa
#include <sys/stat.h>
#include <fcntl.h>

#include <iostream>
#include "ThreadPool.hpp"
#include <sys/sendfile.h>
#include "Statistic.h"
#include "RequestParser.h"
#include "ResponseParser.h"
#include "HttpCodes.h"

using namespace std;

#define BUFFSIZE 1024        // Max text line length
#define PATH_MAX 1024
#define SERVERPORT 3000      // Port
#define SERVER_BACKLOG 1024     // Max number of server connections
#define THPOOL_SIZE 8
int server_socket = -1;
ThreadPool pool(THPOOL_SIZE);

int check(int exp, const char* msg);
void handle_connection(int socket, Statistic *stat, struct sockaddr_in &client_addr);

void destroy() {
    if (server_socket != -1) {
        printf("Closing socket...\n");
        close(server_socket);
        server_socket = -1;
        pool.clear();
    }
}

void catch_sigint(int sig_n) {
    destroy();
    exit(0);
}

int main() {
    signal(SIGINT, catch_sigint);
    Statistic stat;

    struct sockaddr_in server_addr = {0};

    check((server_socket = socket(AF_INET, SOCK_STREAM, 0)), "Failed to create socket");

    // initialize the address struct
    server_addr.sin_family = AF_INET;
    server_addr.sin_addr.s_addr = INADDR_ANY;
    server_addr.sin_port = htons(SERVERPORT);

    check(bind(server_socket, (struct sockaddr*) &server_addr, sizeof(server_addr)), "Bind failed!");
    check(listen(server_socket, SERVER_BACKLOG), "Listen failed!");

    while (1) {
        // wait for and eventually accept incomming connection
        printf("Waiting for connections...\n");

        int addr_size = sizeof(struct sockaddr_in);
        int client_socket;
        struct sockaddr_in client_addr;

        check((client_socket = accept(
                server_socket, (struct sockaddr*) &client_addr, (socklen_t*) &addr_size)),
              "accept failed");

        if (client_socket < 1) continue;

		pool.add(handle_connection, client_socket, &stat, client_addr);
    }

    return 0;
}

int check(int exp, const char *msg) {
    if (exp <= -1) {
        perror(msg);
        exit(1);
    }

    return exp;
}

void sendResponse(int client_socket, int status) {
    ResponseParser res;
    res.fromError(status);
    string response = res.getHeadersStr();
    write(client_socket, response.c_str(), response.length());
    cout << "Closing connection with: " << res.getStatus().first << " " << res.getStatus().second << endl;
    close(client_socket);
}

void handle_connection(int client_socket, Statistic *s, struct sockaddr_in &client_addr) {
    string buffer;
    char c_buff[BUFFSIZE];
    size_t bytes_read;
    char actualpath[PATH_MAX + 1];

    // read the client message -- http GET request
    while ((bytes_read = read(client_socket, c_buff, sizeof(c_buff) - 1)) > 0) {
        buffer.append(c_buff, bytes_read);
        if (buffer.find("\r\n\r\n") != std::string::npos) break; // GET request can not have body, because of that I am not trying to get it
    }

    RequestParser req;
    int status;
    if ((status = req.parse(buffer)) > 299) {
        sendResponse(client_socket, status);
        return;
    }
    char ip[16];
    inet_ntop(AF_INET, &client_addr.sin_addr, ip, sizeof(ip));

    s->add(ip, req.getPath());

    cout << "Statistic: " << s->str() << endl;
    // validity check
    string path = "./static";
    path.append(req.getPath());
    string fileExtension = path.substr(path.find_last_of(".") + 1);

    if (realpath(path.c_str(), actualpath) == NULL) {
        printf("ERROR(bad path): %s\n", buffer.c_str());
        sendResponse(client_socket, HttpStatus_NotFound);
        return;
    }

    // read file and send its contents to client
    int fd = open(path.c_str(), O_RDONLY);
    if (fd < 1) {
        printf("ERROR(open): %s\n", buffer.c_str());
        sendResponse(client_socket, HttpStatus_NotFound);
        return;
    }

    struct stat stat_buf;      /* argument to fstat */
    fstat(fd, &stat_buf);

    ResponseParser res;
    // Setup response headers
    res.setStatus({ HttpStatus_OK, HttpStatus_reasonPhrase(HttpStatus_OK) });
    map<string, string> headers;
    headers[H_CONTENT_LENGTH] = to_string(stat_buf.st_size);
    headers[H_CONNECTION] = "close";

    if (!fileExtension.compare("jpeg")) {
        headers[H_CONTENT_TYPE] = CT_IMAGE_JPEG;
    } else if (!fileExtension.compare("txt")) {
        headers[H_CONTENT_TYPE] = CT_TEXT_PLAIN;
    } else if (!fileExtension.compare("html")) {
        headers[H_CONTENT_TYPE] = CT_TEXT_HTML;
    } else if (!fileExtension.compare("mp4")) {
        headers[H_CONTENT_TYPE] = CT_VIDEO_MP4;
    } else {
        sendResponse(client_socket, HttpStatus_UnsupportedMediaType);
    }

    res.setHeaders(headers);
    res.updateDate();

    string response = res.getHeadersStr();
    write(client_socket, response.c_str(), response.length());

    off_t offset = 0;
    sendfile(client_socket, fd, &offset, stat_buf.st_size);

    printf("Closing connection\n");
    close(client_socket);
}


