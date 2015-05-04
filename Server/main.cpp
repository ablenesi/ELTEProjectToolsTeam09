
#include <stdlib.h>
#include <iostream>

// #include <cppconn/driver.h>
// #include <cppconn/exception.h>
// #include <cppconn/resultset.h>
// #include <cppconn/statement.h>

//#include <thread>

#include "GlobalClass.h"
#include "Entity/MessageEntity.h"
#include "Dao/UserDao.h"


int main(void)
{
	UserDao userDao;
	std::cout << GlobalClass::DATABASE_HOST << userDao.getEntity(2).getUsername()<< std::endl;
}