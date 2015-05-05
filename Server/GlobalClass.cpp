#include "GlobalClass.h"


const std::string GlobalClass::DATABASE_HOST = "tcp://127.0.0.1:3306";
const std::string GlobalClass::DATABASE_NAME = "chat_db";

const std::string GlobalClass::DATABASE_USER = "dbuser";
const std::string GlobalClass::DATABASE_PASSWORD = "dbpassword";



const int GlobalClass::PORT_NUMBER = 9032;


std::string GlobalClass::converter(int a) {
	std::stringstream buffer;
	std::string strin;
	buffer << a;
	buffer >> strin;
	buffer.str("");
	buffer.clear();
	return strin;
}
