#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <netdb.h>
#include <arpa/inet.h>

int main(int argc, char *argv[]) {
    char type[5], path[10], protocol[10];
    char host[35];
    char full_request[60];
    int Status;
    int cliente_socket = socket(AF_INET,SOCK_STREAM,0);
    struct sockaddr_in server;
    
    if(cliente_socket < 0) {
        perror("Erro ao criar o Socket\n");
        close(cliente_socket);        
        exit(1);
    }
    
    server.sin_family = AF_INET;                         // Tipo da conexao
    server.sin_port = htons(9999);                       // Porta
    server.sin_addr.s_addr = inet_addr("127.0.0.1");     // IP 
    bzero(&(server.sin_zero),8);                         // Zera estrutura
    
    
    // Função responsável por estabelecer a conexão com o server pelo socket
    Status = connect(cliente_socket,(struct sockaddr *)&server, sizeof(server)); 
    if(Status < 0)  {  
        close(cliente_socket);        
        exit(1);
    } 
    scanf("%s%s%s", type, path, protocol);
    do{}while(getchar() != '\n');
    do{}while(getchar() != '\n');
    
    sprintf(full_request,"%s %s %s \r\n",type,path,protocol);       

    Status = send(cliente_socket,full_request,strlen(full_request),0);  // Envia requisicao
    if(Status < 0) {
        perror("Erro ao enviar!\n");
        close(cliente_socket);        
        exit(1);
    }

    char buffer[256]; 
    int bytes = 1;                                            //Bytes recebidos
    while(bytes > 0) {                                        // Enquanto estiver recebendo
        memset(buffer,0,256);                                 //Limpa o buffer
        bytes = recv(cliente_socket,buffer,sizeof(buffer),0); // Receberos dados
        printf("%s",buffer);
    }
    close(cliente_socket);
    
    return 0;
}
