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
	/**
	*	Binds server to port.
	*/
	Server(int port);
	/**
	* Closes server socket.
	*/
	~Server();
	/**
	*	\brief Server start waiting for clients on PORT and update existing clients.
	*	Server objects handles clients and threads. For each client creats a thread, it also has a thread in which
		checks the activity of user.
	*/
	void run();
	const int PORT;
private:
	void error(const char *msg);
	void handleClient(int clientSocket);
	void onlineCheckThread();
	ChatHandler chatHandler;
	int sockfd;
	bool isRunning;
};


#endif