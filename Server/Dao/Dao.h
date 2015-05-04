#ifndef DAO_H
#define DAO_H

#include <cppconn/driver.h>
#include <cppconn/exception.h>
#include <cppconn/resultset.h>
#include <cppconn/statement.h>
#include <vector>

#include "../Entity/Entity.h"
#include "../GlobalClass.h"

class Dao {
public:
	Dao();
	~Dao();
	virtual Entity getEntity(int id) = 0;
protected:
	sql::Driver *driver;
	sql::Connection *con;
};

#endif