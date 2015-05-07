#include "Server/Server.h"

void error(const char *msg)
{
	perror(msg);
	exit(1);
}


int main(void)
{
	Server server(GlobalClass::PORT_NUMBER);
	server.run();
	return 0; 
}