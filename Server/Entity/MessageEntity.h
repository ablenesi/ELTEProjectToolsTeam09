#ifndef MESSAGEENTITY_H
#define MESSAGEENTITY_H

#include "Entity.h"
#include "UserEntity.h"
#include <string>

class MessageEntity: public Entity {
private:
	UserEntity user;
	std::string	content;
	int time;
	UserEntity targetUser;
public:
	UserEntity getUser();
	std::string	getContent();
	int getTime();
	UserEntity getTargetUser();

	void setUser(UserEntity _user);
	void setContent(std::string _content);
	void setTime(int _time);
	void setTargetUser(UserEntity _targetUser);
};

#endif
