#ifndef CHATHANDLER_H
#define CHATHANDLER_H

#include <mutex>
#include <iostream>

#include "GlobalClass.h"
#include "../Entity/UserEntity.h"
#include "../Dao/UserDao.h"




class ChatHandler
{
public:
	ChatHandler();
	~ChatHandler();
	std::string registerUser(const std::string &request);
	std::string loginUser(const std::string &request);
	std::string updateUser(const std::string &request);
	std::string messageUser(const std::string &request);
private:
	UserEntity createUserFromRequest(const std::string &request);
	std::mutex _mu;
};


#endif