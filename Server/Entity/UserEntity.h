#ifndef USERENTITY_H
#define USERENTITY_H

#include "Entity.h"
#include <string>

class UserEntity: public Entity {
private:
	std::string username;
	std::string password;
	std::string email;
	long lastUpdate;
public:
	UserEntity();
	UserEntity(std::string _username, std::string _password, std::string _email, long _lastUpdate);

	std::string getUsername();
	std::string getPassword();
	std::string getEmail();
	long getLastUpdate();

	void setUsername(std::string _name);
	void setPassword(std::string _password);
	void setEmail(std::string _email);
	void setLastUpdate(long _lastUpdate);
};

#endif
