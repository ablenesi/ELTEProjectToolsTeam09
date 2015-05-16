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
		code = GlobalClass::INCORRECT_REQUEST;
	} else {
		try {
			UserDao userDao;
			//TO-DO Hash user password
			// user.setPassword(sha256(user.getPassword()))
			long id = userDao.saveUser(user);
			if (id > 0) {
				code = GlobalClass::REQUEST_OK;
			} else {
				code = GlobalClass::USER_NAME_ALREADY_EXIST;
			}
		} catch (sql::SQLException &e) {
			std::cout << "# ERR: SQLException in " << __FILE__;
			std::cout << "(" << __FUNCTION__ << ") on line "
					<< __LINE__ << std::endl;
			std::cout << "# ERR: " << e.what();
			std::cout << " (MySQL error code: " << e.getErrorCode();
			std::cout << ", SQLState: " << e.getSQLState() << " )" << std::endl;
			code = GlobalClass::DATABASE_ERROR;
		}
	}
	response = GlobalClass::converter(code);
	return response;
}

std::string ChatHandler::loginUser(const std::string &request) {

	std::lock_guard<std::mutex> lock(_mu);

	UserEntity user = createUserFromRequest(request);
	std::string response, token;
	int code;

	if (user.getUsername() == "") {
		code = GlobalClass::INCORRECT_REQUEST;
	} else {
		try {
			UserDao userDao;
			user = userDao.getEntity(user.getUsername(), user.getPassword());
			int id = user.getId();
			if (id > 0) {
				int i = 0;
				do {
					token = std::string("RandomToken") + GlobalClass::converter(id +i);
					i++;
				} while (onlineUsers.find(token) != onlineUsers.end());
				onlineUsers[token] = user;
				code = GlobalClass::REQUEST_OK;
			} else {
				code = GlobalClass::INCORRECT_USER_OR_PASSWOROD;
			}
		} catch (sql::SQLException &e) {
			std::cout << "# ERR: SQLException in " << __FILE__;
			std::cout << "(" << __FUNCTION__ << ") on line "
					<< __LINE__ << std::endl;
			std::cout << "# ERR: " << e.what();
			std::cout << " (MySQL error code: " << e.getErrorCode();
			std::cout << ", SQLState: " << e.getSQLState() << " )" << std::endl;
			code = GlobalClass::DATABASE_ERROR;
		}
	}
	response = GlobalClass::converter(code) + GlobalClass::DELIMITER1 + token;

	return response;
}

std::string ChatHandler::updateUser(const std::string token) {
	int code;
	std::lock_guard<std::mutex> lock(_mu);

	UserEntity user = onlineUsers[token];
	
	std::string onlineU = "";
	std::string response;
	if (user.getId() > 0) {
		for(auto userElement : onlineUsers) {
			onlineU += userElement.second.getUsername() + GlobalClass::DELIMITER2;
		}
		MessageDao messageDao;
		std::vector<MessageEntity> privateMessageList = messageDao.getMessages(user, user.getLastUpdate());
		std::vector<MessageEntity> publicMessageList = messageDao.getPublicMessages(user.getLastUpdate());

		std::string privateMsg;
		std::string publicMsg;
		for(auto msg: privateMessageList) {
			privateMsg += msg.getUser().getUsername() + GlobalClass::DELIMITER3 + 
				msg.getContent() + GlobalClass::DELIMITER3 + GlobalClass::converter(msg.getTime())+ GlobalClass::DELIMITER2;
		} 
		for(auto msg: publicMessageList) {
			publicMsg += msg.getUser().getUsername() + GlobalClass::DELIMITER3 + 
				msg.getContent() + GlobalClass::DELIMITER3 + GlobalClass::converter(msg.getTime())+ GlobalClass::DELIMITER2;
		}
		response = onlineU + GlobalClass::DELIMITER1 + privateMsg + GlobalClass::DELIMITER1 + publicMsg;
		//for all the messages
		//onlineUsers[token].setLastUpdate(0);

		std::cout << onlineUsers[token].getUsername() << ": " <<onlineUsers[token].getLastUpdate() << "| |" << privateMessageList.size() << std::endl;
		onlineUsers[token].setLastUpdate(GlobalClass::currentTimeMillis());
		code = GlobalClass::REQUEST_OK;
	} else {
		code = GlobalClass::INCORRECT_REQUEST;
	}

	response  = GlobalClass::converter(code) + GlobalClass::DELIMITER1 + response;
	return response;
}

std::string ChatHandler::messageUser(const std::string &request) {
	MessageEntity msg = createMessageFromRequest(request);
	int code;

	if ((msg.getUser().getId() > 0) && (msg.getTargetUser().getId() != 0)) {
		MessageDao messageDao;
		int id = messageDao.saveMessage(msg);
		if (id > 0) {
			code = GlobalClass::REQUEST_OK;
		} else {
			code = GlobalClass::DATABASE_ERROR;
		}
	} else {
		code = GlobalClass::INCORRECT_REQUEST;
	}
	
	return GlobalClass::converter(code);
}


UserEntity ChatHandler::createUserFromRequest(const std::string &request) {
	std::vector<std::string> userData = GlobalClass::split(request, GlobalClass::DELIMITER1);
	UserEntity user;
	if (userData.size() == 3) user =  UserEntity(userData[0], userData[1], userData[2], GlobalClass::currentTimeMillis());
	else if (userData.size() == 2) user = UserEntity(userData[0], userData[1], "", 0);

	return user;
}

MessageEntity ChatHandler::createMessageFromRequest(const std::string &request) {
	
	std::vector<std::string> messageData = GlobalClass::split(request, GlobalClass::DELIMITER1);
	
	if (messageData.size() < 3) {return MessageEntity();}

	UserDao userDao;
	UserEntity sender = onlineUsers[messageData[0]];
	UserEntity target;
	if (messageData[1] != GlobalClass::COMMON_ROOM_NAME) {
		target = userDao.getEntity(messageData[1]);
	} else {
		target.setId(-1);
	}

	MessageEntity msg( sender,  target, messageData[2], GlobalClass::currentTimeMillis());
	return msg;
}

void ChatHandler::checkOnlineUsers() {
	auto itr = onlineUsers.begin();
	long threshold =  GlobalClass::currentTimeMillis() - GlobalClass::ONLINECHECK_SLEEPTIME.count();
	while (itr != onlineUsers.end()) {
		if (itr->second.getLastUpdate() < threshold) {
			onlineUsers.erase(itr++);
		} else {
			++itr;
		}
	}
}

