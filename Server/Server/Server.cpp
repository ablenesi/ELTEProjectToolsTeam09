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
	
	sockaddr_in cli_addr;
	socklen_t clilen = sizeof(cli_addr);
	int newsockfd;

	std::cout << "Waiting for clients..." << std::endl;

	while ((newsockfd = accept(sockfd, (sockaddr *) &cli_addr, &clilen)) >= 0) {

		std::thread clientHandlerThread(&Server::handleClient, this, newsockfd);
		clientHandlerThread.detach();
	}
}


void Server::handleClient(int clientSocket) {
	std::cout << "Processing client ..." << std::endl;
	char buffer[1024];
	int n;
	std::string command, message, response;
	

	while ((n = read(clientSocket,buffer, sizeof(buffer))) > 0) {		
		std::cout << "Running!! " << n << std::endl;

		buffer[3] = 0;
		buffer[n] = 0;

		command = buffer;
		message = (buffer + 4);
		message = GlobalClass::trim(message);
	
		if (command == "REG") {
			response = chatHandler.registerUser(message);
		} else if (command == "LOG"){
			response = chatHandler.loginUser(message);
		} else if (command == "UPD") {
			response = chatHandler.updateUser(message);
		} else if (command == "MES") {
			response = chatHandler.messageUser(message);
		} else if (command == "OUT") {
			std::cout << "Client end";
			break;
		}
		response += "\n";

		const char* responseData = response.c_str();
		n = write(clientSocket,responseData, response.length()*sizeof(char));
	}
	close(clientSocket);
}


