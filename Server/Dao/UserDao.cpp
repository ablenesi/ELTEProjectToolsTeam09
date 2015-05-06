#include "UserDao.h"



UserEntity UserDao::getEntity(int id) {
	UserEntity user;
	
	sql::PreparedStatement* stmt = con->prepareStatement(
		"SELECT id, username, password, email, last_update FROM `chat_user` WHERE `id` = ?"
	);
	stmt->setInt(1, id);
	stmt->execute();
	sql::ResultSet* res = stmt->getResultSet();;
	
	if (res->next()) {
		user.setId(res->getInt("id"));
		user.setUsername(res->getString("username"));
		user.setPassword(res->getString("password"));
		user.setEmail(res->getString("email"));
		user.setLastUpdate(res->getInt("last_update"));
		
	}
	delete res;
	delete stmt;

	return user;
}


UserEntity UserDao::getEntity(std::string username) {
	UserEntity user;
	
	sql::PreparedStatement* stmt = con->prepareStatement(
		"SELECT id, username, password, email, last_update FROM `chat_user` WHERE `username` = ?"
	);
	stmt->setString(1, username);
	stmt->execute();
	sql::ResultSet* res = stmt->getResultSet();
	if (res->next()) {
		user.setId(res->getInt("id"));
		user.setUsername(res->getString("username"));
		user.setPassword(res->getString("password"));
		user.setEmail(res->getString("email"));
		user.setLastUpdate(res->getInt("last_update"));
		
	}
	delete res;
	delete stmt;

	return user;
}

UserEntity UserDao::getEntity(std::string username, std::string password) {
	UserEntity user;
	
	sql::PreparedStatement* stmt = con->prepareStatement(
		"SELECT id, email, last_update FROM `chat_user` WHERE `username` = ? AND `password` = ?"
	);
	stmt->setString(1, username);
	stmt->setString(2, password);

	sql::ResultSet* res = stmt->executeQuery();
	if (res->next()) {
		user.setId(res->getInt("id"));
		user.setEmail(res->getString("email"));
		user.setLastUpdate(res->getInt("last_update"));
		
	}

	delete stmt;

	return user;
}


long UserDao::saveUser(UserEntity user) {
	int id;
	UserEntity tempUser = getEntity(user.getUsername());
	if (tempUser.getUsername() == "") {
		sql::PreparedStatement* stmt = con->prepareStatement(
			std::string("INSERT INTO `chat_user`(`username`,`password`,`email`,`last_update`) ") +
			"VALUES (?,?,?,?)");
		stmt->setString(1, user.getUsername());
		stmt->setString(2, user.getPassword());
		stmt->setString(3, user.getEmail());
		stmt->setInt(4, user.getLastUpdate());
		stmt->execute();
		id = last_insert_id();
		delete stmt;
	} else {
		id = -1;
	}
	return id;
}

void UserDao::updateUser(UserEntity user) {
	sql::PreparedStatement* stmt = con->prepareStatement(
		std::string("UPDATE `chat_user` SET`username` = ?,`password` = ?,`email` = ?,`last_update` = ? ") +
		"WHERE `id` = ?");
	stmt->setString(1, user.getUsername());	
	stmt->setString(2, user.getPassword());
	stmt->setString(3, user.getEmail());	
	stmt->setInt(4, user.getLastUpdate());
	stmt->setInt(5, user.getId());

	stmt->execute();
	delete stmt;
}

void UserDao::removeUser(long id) {
	sql::PreparedStatement* stmt = con->prepareStatement(
		"DELTE FROM chat_user WHERE id=?"
		);
	stmt->setInt(1, id);
	stmt->execute();
}




