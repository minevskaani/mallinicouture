#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <signal.h>

#include <unistd.h> // fork

#include <sys/types.h>
#include <sys/socket.h>
#include <sys/select.h>
#include <netinet/in.h>
#include <arpa/inet.h> // inet_ntoa

#define BUFFSIZE 4096        // Max text line length
#define SERVERPORT 8081      // Port
#define SERVER_BACKLOG 4            // Max number of server connections
int sockfd = -1;

int check(int exp, const char* msg);
void print(int number);

char* itoa(int value, char* result, int base) {
    // check that the base if valid
    if (base < 2 || base > 36) { *result = '\0'; return result; }

    char* ptr = result, *ptr1 = result, tmp_char;
    int tmp_value;

    do {
        tmp_value = value;
        value /= base;
        *ptr++ = "zyxwvutsrqponmlkjihgfedcba9876543210123456789abcdefghijklmnopqrstuvwxyz" [35 + (tmp_value - value * base)];
    } while ( value );

    // Apply negative sign
    if (tmp_value < 0) *ptr++ = '-';
    *ptr-- = '\0';
    while(ptr1 < ptr) {
        tmp_char = *ptr;
        *ptr--= *ptr1;
        *ptr1++ = tmp_char;
    }
    return result;
}

void destroy() {
    if (sockfd != -1) {
        printf("Closing socket...\n");
        close(sockfd);
        sockfd = -1;
    }
}

void catch_sigint(int sig_n) {
    destroy();
    exit(0);
}

int main() {
    int server_socket, client_socket, addr_size;
    short port = SERVERPORT;
    int backlog = SERVER_BACKLOG;

    signal(SIGINT, catch_sigint);

    struct sockaddr_in server_addr;
    memset(&server_addr, 0, sizeof(server_addr));

    check((server_socket = socket(AF_INET, SOCK_DGRAM, 0)), "Failed to create socket!");

    // Init address structure
    server_addr.sin_family = AF_INET;
    server_addr.sin_addr.s_addr = INADDR_ANY;
    server_addr.sin_port = htons(port);

    check(bind(server_socket, (struct sockaddr*)&server_addr, sizeof(server_addr)), "Bind Failed!");

    sockfd = server_socket;

    for (;;) {
        puts("Waiting for requests...");

        // do whatever we should do with connections.
        // handle_connection(client_socket);
        int buffer;
        size_t bytes_read;

        // read the client's message -- a number
        bytes_read = read(server_socket, &buffer, sizeof(buffer));

        check(bytes_read, "recv error");

        printf("REQUEST: %i\n", buffer);
        fflush(stdout);
        print(buffer);
    }

    destroy();

    return 0;
}


int check(int exp, const char* msg) {
    if (exp == -1) {
        perror(msg);
        exit(1);
    }
    
    return exp;
}

void print(int number) {
    char dec[20];
    char bin[20];
    char hex[20];
    char oct[20];
    char sen[20]; // base 6

    itoa(number, dec, 10);
    itoa(number, bin, 2);
    itoa(number, hex, 16);
    itoa(number, oct, 8);
    itoa(number, sen, 6);

    printf("------------------------------------------------------\n");
    printf("Decimal:     %11s\n", dec);
    printf("Binary:      %11s\n", bin);
    printf("Hexadecimal: %11s\n", hex);
    printf("Octal:       %11s\n", oct);
    printf("Senary:      %11s\n", sen);
    printf("------------------------------------------------------\n");
    fflush(stdout);
}