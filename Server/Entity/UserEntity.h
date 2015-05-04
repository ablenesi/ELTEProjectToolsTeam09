#ifndef USERENTITY_H
#define USERENTITY_H

#include "Entity.h"
#include <string>

class UserEntity: public Entity {
private:
	std::string username;
	std::string password;
	std::string email;
	int lastUpdate;
public:
	std::string getUsername();
	std::string getPassword();
	std::string getEmail();
	int getLastUpdate();

	void setUsername(std::string _name);
	void setPassword(std::string _password);
	void setEmail(std::string _email);
	void setLastUpdate(int _lastUpdate);
};

#endif
