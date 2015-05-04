#include "Dao.h"

Dao::Dao() {
	driver = get_driver_instance();
	con = driver->connect(GlobalClass::DATABASE_HOST, GlobalClass::DATABASE_USER, GlobalClass::DATABASE_PASSWORD);
	/* Connect to the MySQL test database */
	con->setSchema(GlobalClass::DATABASE_NAME);
}

Dao::~Dao() {
	delete con;
}