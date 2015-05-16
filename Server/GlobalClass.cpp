#include "GlobalClass.h"


std::mutex GlobalClass::_muTrim;
std::mutex GlobalClass::_muSplit;
std::mutex GlobalClass::_muConvert;

const std::string GlobalClass::DATABASE_HOST = "tcp://127.0.0.1:3306";
const std::string GlobalClass::DATABASE_NAME = "chat_db";

const std::string GlobalClass::DATABASE_USER = "dbuser";
const std::string GlobalClass::DATABASE_PASSWORD = "dbpassword";

const std::string GlobalClass::SALT = "901d709a83710d7fe1dd0e8d3be2347b90c1cbb284c6b9f753a234dd94edfcca";

const int GlobalClass::COMMON_USER_ID = -1;
const std::string GlobalClass::COMMON_ROOM_NAME = "COMMON";

const int GlobalClass::REQUEST_OK = 0;
const int GlobalClass::INCORRECT_REQUEST= -1;
const int GlobalClass::USER_NAME_ALREADY_EXIST = -2;
const int GlobalClass::INCORRECT_USER_OR_PASSWOROD = -3;
const int GlobalClass::DATABASE_ERROR = -4;


const char GlobalClass::DELIMITER1 = (char)37;
const char GlobalClass::DELIMITER2 = (char)38;
const char GlobalClass::DELIMITER3 = (char)39;

const int GlobalClass::PORT_NUMBER = 9032;

const std::chrono::milliseconds GlobalClass::ONLINECHECK_SLEEPTIME = std::chrono::milliseconds(5000);


std::vector<std::string> GlobalClass::split(const std::string &s, char delim) {
	std::lock_guard<std::mutex> lock(_muSplit);

	std::vector<std::string> elems;
	std::stringstream ss(s);
	std::string item;
	while (std::getline(ss, item, delim)) {
		elems.push_back(trim(item));
	}
	
	return elems;
}

std::string GlobalClass::trim(const std::string& str, const std::string& whitespace)
{
	std::lock_guard<std::mutex> lock(_muTrim);

	const auto strBegin = str.find_first_not_of(whitespace);
	if (strBegin == std::string::npos)
		return ""; // no content

	const auto strEnd = str.find_last_not_of(whitespace);
	const auto strRange = strEnd - strBegin + 1;

	return str.substr(strBegin, strRange);
}

std::string GlobalClass::converter(int a) {
	std::lock_guard<std::mutex> lock(_muConvert);
	std::stringstream buffer;
	std::string strin;
	buffer << a;
	buffer >> strin;
	buffer.str("");
	buffer.clear();
	return strin;
}

long GlobalClass::currentTimeMillis() {
	std::chrono::milliseconds ms = std::chrono::duration_cast< std::chrono::milliseconds >(
	std::chrono::system_clock::now().time_since_epoch());	
	return ms.count();
}

