#include "UserEntity.h"

UserEntity::UserEntity() {
	id = 0;
	username = "";
	password = "";
	email = "";
	lastUpdate = 0;
}

UserEntity::UserEntity(int _id) {
	id = _id;
}

UserEntity::UserEntity(std::string _username, std::string _password, std::string _email, long _lastUpdate) {
	username = _username;
	password = _password;
	email = _email;
	lastUpdate = _lastUpdate;
}

std::string UserEntity::getUsername() {
	return username;
}

std::string UserEntity::getPassword() {
	return password;
}

std::string UserEntity::getEmail() {
	return email;
}

long UserEntity::getLastUpdate() {
	return lastUpdate;
}

void UserEntity::setUsername(std::string _username) {
	username = _username;
}

void UserEntity::setPassword(std::string _password) {
	password = _password;
}

void UserEntity::setEmail(std::string _email) {
	email = _email;
}

void UserEntity::setLastUpdate(long _lastUpdate) {
	lastUpdate = _lastUpdate;
}
