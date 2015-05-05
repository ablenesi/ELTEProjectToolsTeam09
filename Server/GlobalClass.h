#ifndef GLOBALCLASS_H
#define GLOBALCLASS_H

#include <string>
#include <ctime>
#include <sstream>

class GlobalClass {
public:


	static const std::string DATABASE_HOST;
	static const std::string DATABASE_NAME;
	static const std::string DATABASE_USER;
	static const std::string DATABASE_PASSWORD;

	static const int PORT_NUMBER;

	static std::string converter(int a); 
};


#endif

