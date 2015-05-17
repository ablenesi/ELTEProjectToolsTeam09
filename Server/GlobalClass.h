#ifndef GLOBALCLASS_H
#define GLOBALCLASS_H

#include <string>
#include <ctime>
#include <sstream>
#include <vector>
#include <mutex>

/*! Class with constants and some utility functions */
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

	/**
	* String which will be added to password before hashing, adding more security.
	*/
	static const std::string SALT;

	static const int PORT_NUMBER;
	/**
	 *Delimiter which is used to separe the messages in the request
	*/
	static const char DELIMITER1;
	/**
	 *Delimiter which is used to separe the messages sub-parts.
	*/
	static const char DELIMITER2;
	/**
	 *Additional delimiter.
	*/
	static const char DELIMITER3;

	/**
	*	Common room user id.
	*/
	static const int COMMON_USER_ID;
	
	/**
	*	Common room username
	*/
	static const std::string COMMON_ROOM_NAME;

	/*
	* Response code which indicates that the request was processed correctly.	*
	*/
	static const int REQUEST_OK;
	static const int USER_NAME_ALREADY_EXIST;
	/*
	* Error code which means that the request format is invalide.
	*/
	static const int INCORRECT_REQUEST;
	static const int INCORRECT_USER_OR_PASSWOROD;
	static const int DATABASE_ERROR;

	/*
	* Time interval in which the online checker thread checks the users activity.
	*/
	static const std::chrono::milliseconds ONLINECHECK_SLEEPTIME;

	 /**
		* \param str string which will be trimed
		* \param whitespace characters which should be considered white space.
		* \return Trimed string
		* 
		* Remove space from the begining and ending of the string
	*/
	static std::string trim(const std::string& str, const std::string& whitespace = " \t\n");

	/**
		* \param s input string
		* \param delim delimiter character, these wont be part of the return value
		* \return a std::vector of strings, each of which is a substring of string formed by splitting it on boundaries formed by the char delimiter.
		* 
	*/
	static std::vector<std::string> split(const std::string &s, char delim);

	/**
		Converts integer to string
		* \param a input integer
		* \return string interpretation of the parameter
	*/
	static std::string converter(int a);

	/**
		Time since epoch.
		* \return milliseconds passed since 1970-1-1.
	*/
	static long currentTimeMillis();
};


#endif

