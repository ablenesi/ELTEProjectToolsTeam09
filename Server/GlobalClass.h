#ifndef GLOBALCLASS_H
#define GLOBALCLASS_H

#include <string>
#include <ctime>
#include <sstream>
#include <vector>
#include <mutex>


class GlobalClass {
private:
	static std::mutex _muTrim;
	static std::mutex _muSplit;
	static std::mutex _muConvert;
public:
	static const std::string DATABASE_HOST;
	static const std::string DATABASE_NAME;
	static const std::string DATABASE_USER;
	static const std::string DATABASE_PASSWORD;

	static const std::string SALT;

	static const int PORT_NUMBER;

	static const char DELIMITER1;
	static const char DELIMITER2;
	static const char DELIMITER3;

	static const int COMMON_USER_ID;
	static const std::string COMMON_ROOM_NAME;

	static const int REQUEST_OK;
	static const int USER_NAME_ALREADY_EXIST;
	static const int INCORRECT_REQUEST;
	static const int INCORRECT_USER_OR_PASSWOROD;
	static const int DATABASE_ERROR;

	static std::string trim(const std::string& str, const std::string& whitespace = " \t\n");
	static std::vector<std::string> split(const std::string &s, char delim);
	static std::string converter(int a);
	static long currentTimeMillis();
};


#endif

