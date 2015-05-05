#ifndef SERVER_H
#define SERVER_H

#include <string>
#include <iostream>
#include <vector>
#include <thread>

#include <unistd.h>
#include <sys/types.h> 
#include <sys/socket.h>
#include <netinet/in.h>

class Server {
public:
	Server(int port);
	~Server();
	void run();
	const int PORT;
private:
	void error(const char *msg);
	void handleClient(int clientSocket);
	int sockfd;
};


#endif