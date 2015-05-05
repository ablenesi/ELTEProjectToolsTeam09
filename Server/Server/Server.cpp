#include "Server.h"

Server::Server(int port):PORT(port) {

	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if (sockfd < 0) 
		error("ERROR opening socket");

	sockaddr_in serv_addr = {0};

	serv_addr.sin_family = AF_INET;
	serv_addr.sin_addr.s_addr = INADDR_ANY;
	serv_addr.sin_port = htons(PORT);
	if (bind(sockfd, (sockaddr *) &serv_addr, sizeof(serv_addr)) < 0) 
		error("ERROR on binding");
}

Server::~Server() {
	close(sockfd);
}

void Server::error(const char *msg)
{
	perror(msg);
	exit(1);
}


void Server::run() {
	listen(sockfd,5);
	
	socklen_t clilen;
	sockaddr_in cli_addr;
	int newsockfd;

	while (true) {
		newsockfd = accept(sockfd, (sockaddr *) &cli_addr, &clilen);
		
		if (newsockfd < 0) {
			perror("ERROR on accept");
			exit(1);
		}
		std::thread t1(&Server::handleClient, this, newsockfd);
		t1.detach();
	}
}

void Server::handleClient(int clientSocket) {
	char buffer[256];
	int n = read(newsockfd,buffer,255);
	std::string message(buffer);
	std::string command = message.subst(0, 3);

	if (command == "REG") {

	} else if (command == "LOG"){

	} else if (command == "UPD") {

	} else if (command == "MES") {

	}
	


	close(newsockfd);
}


