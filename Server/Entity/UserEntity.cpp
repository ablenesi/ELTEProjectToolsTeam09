#include "UserEntity.h"



std::string UserEntity::getUsername() {
	return username;
}

std::string UserEntity::getPassword() {
	return password;
}

std::string UserEntity::getEmail() {
	return email;
}

int UserEntity::getLastUpdate() {
	return lastUpdate;
}

void UserEntity::setUsername(std::string _Username) {
	username = _username;
}

void UserEntity::setPassword(std::string _password) {
	password = _password;
}

void UserEntity::setEmail(std::string _email) {
	email = _email;
}

void UserEntity::setLastUpdate(int _lastUpdate) {
	lastUpdate = _lastUpdate;
}
