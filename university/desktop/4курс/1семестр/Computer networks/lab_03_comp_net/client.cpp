#include <cstdio>
#include <cstdlib>
#include <unistd.h>

#include <sys/socket.h>

#include <netinet/in.h>
#include <netdb.h>
#include <iostream>
#include "RequestParser.h"
#include "HttpCodes.h"
#include "ResponseParser.h"

// open()
#include <fcntl.h>

#define SERV_PORT 3000
#define BUFF_SIZE 12
#define RES_DIR "./downloads"

void handle_response(int sd, char* path);

int main(int argc, char** argv) {
    if (argc != 2 || argv[1][0] != '/') {
        cout << "Usage: " << argv[0] << " uri" << endl;
        cout << "uri should start with /" << endl;

        exit(1);
    }

    char* msg = argv[1];

    printf("Creating socket...");
    int sfd = socket(AF_INET, SOCK_STREAM, 0);

    if (sfd < 0) {
        printf("Can not create socket\n");
        return -1;
    }

    struct hostent* host = gethostbyname("localhost");
    if (!host) {
        perror("Error in gethostbyname\n");
        exit(1);
    }
    struct sockaddr_in server_addr = {
      .sin_family = AF_INET,
      .sin_addr = *((struct in_addr*) host->h_addr_list[0])
    };
    server_addr.sin_port = htons(SERV_PORT);

    if (connect(sfd, (struct sockaddr*) &server_addr, sizeof(server_addr)) < 0) {
      perror("connect failed\n");
      exit(1);
    }

    RequestParser req;
    req.setProtocol("HTTP/1.1");
    map<string, string> headers;
    headers[H_HOST] = "LOCALHOST";
    req.setHeaders(headers);
    req.updateDate();
    req.setMethod("GET");
    req.setPath(msg);
    string request = req.str();

    sendto(sfd, &request[0], request.length(), 0, (struct sockaddr*)&server_addr, sizeof(struct sockaddr));

    cout << "Sent message:\n" << request << endl;

    handle_response(sfd, msg);

    close(sfd);
    close(sfd);

    return 0;
}

void handle_response(int sd, char* path) {
    string file_path = RES_DIR;
    file_path.append(path);

    char c_buff[BUFF_SIZE];
    string buffer;

    int bytes_read;
    while ((bytes_read = read(sd, c_buff, sizeof(c_buff))) > 0) {
        buffer.append(c_buff, bytes_read);
        if (buffer.find("\r\n\r\n") != std::string::npos) break; // header part ended
    }

    ResponseParser res;
    if (res.parse(buffer) < 1) {
        cout << "ERROR(ResponseParser::parse). Received msg:\n" << buffer << endl;
    }

    cout << "ResponseParser header part:" << endl;
    cout << res.getHeadersStr() << endl;

    if (res.getStatus().first > 299) {
        return;
    }

    int fd = open(&file_path[0], O_WRONLY | O_CREAT, 0666);
    if (fd < 1) {
        cout << "ERROR: Can not open file: " << file_path << " for writing" << endl;
        perror("");
        return;
    }

    string bodyChunk = res.getBody();
    // write parsed part of file
    write(fd, &bodyChunk[0], bodyChunk.length());
    auto headers = res.getHeaders();
    if (headers[H_CONTENT_LENGTH].empty()) {
        cout << "Error: no " << H_CONTENT_LENGTH << " header";
    } else {
        int contentLength = stoi(headers[H_CONTENT_LENGTH], 0, 10);
        contentLength -= bodyChunk.length();
        // write file
        while (contentLength > 0) {
            bytes_read = read(sd, c_buff, sizeof(c_buff));
            if (bytes_read < 0) break;
            contentLength -= bytes_read;
            write(fd, c_buff, bytes_read);
        }
    }

    cout << "File saved in: " << file_path << endl;
    close(fd);
}
