#ifndef CHATHANDLER_H
#define CHATHANDLER_H

#include <iostream>

#include "../GlobalClass.h"
#include "../Entity/UserEntity.h"
#include "../Entity/MessageEntity.h"
#include "../Dao/UserDao.h"
#include "../Dao/MessageDao.h"



class ChatHandler
{
public:	
	ChatHandler();
	~ChatHandler();
	/**
		* \param request register string request sent by client, format: username:delimiter1:password:delimiter1:email@email.com.
		* \return response string containing the code of the operation (success/fail)
		* 
		* Register an user in the database if the request data is correct, otherwise it returns with an error code.
	*/
	std::string registerUser(const std::string &request);
	/**
		* \param request login string request sent by client, format: username:delimiter1:password.
		* \return response string containing the code of the operation (success/fail), and user random user token on success
		* 
		* If the username/password combination user exist in the database it returns a token. With the token the client can
		* access the users messages and send messages to other users.
	*/
	std::string loginUser(const std::string &request);
	/**
		* \param token the user token generated on login
		* \return response string containing the code of the operation (success/fail), and update data format: error_code:delimit1:online_users:delimit1:private_msg:public_msg 
		* If the token a valid one, it will update user last activity time, 
	*/
	std::string updateUser(const std::string token);
	/**
		* \param request message string request sent by client, format: TOKEN:delimiter1:TARGET:delimiter1:This is the message:delimiter1:
		* \return response string containing the code of the operation (success/fail)
		* 
		* If the request information is valid, it will send message from the token user to the target user
	*/
	std::string messageUser(const std::string &request);
	/**
	* It will remove all inactive user from the online user list.
	*/
	void checkOnlineUsers();
private:
	UserEntity createUserFromRequest(const std::string &request);
	MessageEntity createMessageFromRequest(const std::string &request);
	std::map<std::string, UserEntity> onlineUsers;
	std::mutex _mu;
};


#endif