#ifndef USERDAO_H
#define USERDAO_H

#include "Dao.h"
#include "../Entity/UserEntity.h"

class UserDao : public Dao {
public:
	UserEntity getEntity(int id);
};

#endif
