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

	//while (true) {
		std::cout << "Waiting for clients..." << std::endl;
		newsockfd = accept(sockfd, (sockaddr *) &cli_addr, &clilen);
		
		if (newsockfd < 0) {
			perror("ERROR on accept");
			exit(1);
		}
		std::thread clientHandlerThread(&Server::handleClient, this, newsockfd);
		//clientHandlerThread.detach();
		clientHandlerThread.join();
	//}
}


void Server::handleClient(int clientSocket) {
	std::cout << "Processing client ..." << std::endl;
	char buffer[1024];
	int n = read(clientSocket,buffer, sizeof(buffer));
	buffer[3] = 0;

	std::string command(buffer);
	std::string message(buffer + 4);

	std::string response;

	if (command == "REG") {
		response = chatHandler.registerUser(message);
	} else if (command == "LOG"){
		response = chatHandler.loginUser(message);
	} else if (command == "UPD") {
		response = chatHandler.updateUser(message);
	} else if (command == "MES") {
		response = chatHandler.messageUser(message);
	}

	const char* responseData = response.c_str();
	n = write(clientSocket,responseData, response.length()*sizeof(char));


	close(clientSocket);
}


