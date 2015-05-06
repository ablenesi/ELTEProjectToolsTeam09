#include "ChatHandler.h"

ChatHandler::ChatHandler() {

}

ChatHandler::~ChatHandler() {

}

std::string ChatHandler::registerUser(const std::string &request) {
	UserEntity user = createUserFromRequest(request);
	std::string response;
	std::string token;
	int code;
	if (user.getUsername() == "") {
		code = GlobalClass::INCORRECT_REQUEST_FORMAT;
	} else {
		UserDao userDao;
		//TO-DO Hash user password
		// user.setPassword(sha256(user.getPassword()))
		long id = userDao.saveUser(user);
		if (id > 0) {
			code = GlobalClass::REQUEST_OK;
			// TO-DO Hashing needs to be implemented ( something like SHA256)
			// token = sha256(rand()) example
			// example: http://www.zedwood.com/article/cpp-sha256-function
			token = "SomeRandomToken" + id;
		} else {
			code = GlobalClass::USER_NAME_ALREADY_EXIST;
		}
	}
	response = GlobalClass::converter(code);
	response += GlobalClass::DELIMITER1 + token;


	return response;
}

std::string ChatHandler::loginUser(const std::string &request) {
	return "OK VVorking";
}

std::string ChatHandler::updateUser(const std::string &request) {
	return "OK VVorking";
}

std::string ChatHandler::messageUser(const std::string &request) {
	return "OK VVorking";
}


UserEntity ChatHandler::createUserFromRequest(const std::string &request) {
	std::vector<std::string> userData = GlobalClass::split(request, GlobalClass::DELIMITER1);
	return (userData.size() == 3) ? UserEntity(userData[0], userData[1], userData[2], time(0)):
			UserEntity();
}