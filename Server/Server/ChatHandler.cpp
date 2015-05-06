#include "ChatHandler.h"

ChatHandler::ChatHandler() {

}

ChatHandler::~ChatHandler() {

}

std::string ChatHandler::registerUser(const std::string &request) {
	UserEntity user = createUserFromRequest(request);
	std::string response;
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
		} else {
			code = GlobalClass::USER_NAME_ALREADY_EXIST;
		}
	}
	response = GlobalClass::converter(code);

	return response;
}

std::string ChatHandler::loginUser(const std::string &request) {
	UserEntity user = createUserFromRequest(request);
	std::string response, token;
	int code;

	if (user.getUsername() == "") {
		code = GlobalClass::INCORRECT_REQUEST_FORMAT;
	} else {
		UserDao userDao;
		user = userDao.getEntity(user.getUsername(), user.getPassword());
		int id = user.getId();
		if (id > 0) {
			int i = 0;
			do {
				token = std::string("RandomToken") + GlobalClass::converter(id +i);
				i++;
			} while (onlineUsers.find(token) != onlineUsers.end());
			onlineUsers["token"] = user;
			code = GlobalClass::REQUEST_OK;
		} else {
			code = GlobalClass::INCORRECT_USER_OR_PASSWOROD;
		}
	}
	response = GlobalClass::converter(code) + GlobalClass::DELIMITER1 + token;

	return response;
}

std::string ChatHandler::updateUser(const std::string &request) {
	return "OK VVorking";
}

std::string ChatHandler::messageUser(const std::string &request) {
	return "OK VVorking";
}


UserEntity ChatHandler::createUserFromRequest(const std::string &request) {
	std::vector<std::string> userData = GlobalClass::split(request, GlobalClass::DELIMITER1);
	UserEntity user;
	if (userData.size() == 3) user =  UserEntity(userData[0], userData[1], userData[2], time(0));
	else if (userData.size() == 2) user = UserEntity(userData[0], userData[1], "", 0);

	return user;
}