#include "MessageEntity.h"

UserEntity MessageEntity::getUser() {
	return user;
}

std::string	MessageEntity::getContent() {
	return content;
}

int MessageEntity::getTime() {
	return time;
}

UserEntity MessageEntity::getTargetUser() {
	return targetUser;
}


void MessageEntity::setUser(UserEntity _user) {
	user = _user;
}

void MessageEntity::setContent(std::string _content) {
	content = _content;
}

void MessageEntity::setTime(int _time) {
	time = _time;
}

void MessageEntity::setTargetUser(UserEntity _targetUser) {
	targetUser = _targetUser;
}
