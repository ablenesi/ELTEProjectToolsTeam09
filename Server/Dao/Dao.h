#ifndef DAO_H
#define DAO_H

#include <cppconn/driver.h>
#include <cppconn/exception.h>
#include <cppconn/resultset.h>
#include <cppconn/statement.h>
#include <cppconn/prepared_statement.h>
#include <vector>

#include "../Entity/Entity.h"
#include "../GlobalClass.h"

class Dao {
public:
	Dao();
	~Dao();
	long last_insert_id();
protected:
	sql::Driver *driver;
	sql::Connection *con;
};

#endif