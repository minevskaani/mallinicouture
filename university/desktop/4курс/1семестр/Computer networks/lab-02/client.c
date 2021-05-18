#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#include <sys/socket.h>
#include <sys/types.h>

#include <netinet/in.h>
#include <netinet/ip.h>
#include <netdb.h>

#define SERV_PORT 8081
#define SERV_ADDR "127.0.0.1"
#define MSG_LEN 256

int send_number(int number, const int fd, const struct sockaddr *server_addr) {
    sendto(fd, &number, sizeof(number), 0, server_addr, sizeof(struct sockaddr));
    printf("Sended message: '%i'\n", number);
}

int main() {

    printf("Number which will be send to server: ");
    int number;
    scanf("%i", &number);

    printf("Creating socket...");
    int sfd = socket(AF_INET, SOCK_DGRAM, 0);

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
      .sin_addr = *((struct in_addr*) host->h_addr_list[0]),
      .sin_port = htons(SERV_PORT)
    };
    
    send_number(number, sfd, (struct sockaddr*)&server_addr);

    close(sfd);

    return 0;
}