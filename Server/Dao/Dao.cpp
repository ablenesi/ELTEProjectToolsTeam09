#include "Dao.h"

Dao::Dao() {
	driver = get_driver_instance();
	driver->threadInit();
	con = driver->connect(GlobalClass::DATABASE_HOST, GlobalClass::DATABASE_USER, GlobalClass::DATABASE_PASSWORD);
	/* Connect to the MySQL test database */
	con->setSchema(GlobalClass::DATABASE_NAME);
}

Dao::~Dao() {
	driver->threadEnd();
	delete con;
}

long Dao::last_insert_id() {

	long id;
	sql::Statement *stmt = con->createStatement();
	sql::ResultSet *res = stmt->executeQuery("SELECT LAST_INSERT_ID() as id;");
	res->next();
	id = res->getInt("id");
	delete res;
	delete stmt;
	return id;
}

