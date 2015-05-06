#ifndef USERDAO_H
#define USERDAO_H

#include "Dao.h"
#include "../Entity/UserEntity.h"

class UserDao : public Dao {
public:
	UserEntity getEntity(int id);
	UserEntity getEntity(std::string username);
	UserEntity getEntity(std::string username, std::string password);
	long saveUser(UserEntity user);
	void updateUser(UserEntity user);
	void removeUser(long id);
};

#endif
