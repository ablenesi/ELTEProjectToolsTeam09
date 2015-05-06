#include "GlobalClass.h"


const std::string GlobalClass::DATABASE_HOST = "tcp://127.0.0.1:3306";
const std::string GlobalClass::DATABASE_NAME = "chat_db";

const std::string GlobalClass::DATABASE_USER = "dbuser";
const std::string GlobalClass::DATABASE_PASSWORD = "dbpassword";

const std::string GlobalClass::SALT = "gWiSdUQMZUXdT6ocMK3Y6Mki9u2MavEKcrHXyvxlKBGh";

const char GlobalClass::DELIMITER1 = (char)37;
const char GlobalClass::DELIMITER2 = (char)38;

const int GlobalClass::PORT_NUMBER = 9032;


std::vector<std::string> GlobalClass::split(const std::string &s, char delim) {
	std::vector<std::string> elems;
	std::stringstream ss(s);
	std::string item;
	while (std::getline(ss, item, delim)) {
		elems.push_back(item);
	}
	return elems;
}

std::string GlobalClass::converter(int a) {
	std::stringstream buffer;
	std::string strin;
	buffer << a;
	buffer >> strin;
	buffer.str("");
	buffer.clear();
	return strin;
}
