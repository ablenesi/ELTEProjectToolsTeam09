#ifndef SERVER_H
#define SERVER_H

#include <thread>

#include <unistd.h>
#include <sys/types.h> 
#include <sys/socket.h>
#include <netinet/in.h>

#include "ChatHandler.h"

class Server {
public:
	Server(int port);
	~Server();
	void run();
	const int PORT;

private:
	ChatHandler chatHandler;
	void error(const char *msg);
	void handleClient(int clientSocket);
	int sockfd;
};


#endif