#include "MessageDao.h"

MessageEntity MessageDao::getEntity(int id) {
	MessageEntity msg;
	
	sql::PreparedStatement* stmt = con->prepareStatement(
		std::string("SELECT user_id, username, content, target_id, time ") + 
		"FROM `chat_message` " +
		"JOIN `chat_user` on `chat_message`.user_id = `chat_user`.id WHERE `chat_user`.`id` = ?"
	);
	stmt->setInt(1, id);
	stmt->execute();
	sql::ResultSet* res = stmt->getResultSet();
	UserEntity tempUser;
	if (res->next()) {
		msg.setId(id);
		tempUser.setId(res->getInt("user_id"));
		tempUser.setUsername(res->getString("username"));
		msg.setTargetUser(tempUser);
		tempUser.setId(res->getInt("target_id"));
		msg.setUser(tempUser);
		msg.setContent(res->getString("content"));
		msg.setTime(res->getInt("time"));
	}
	delete res;
	delete stmt;

	return msg;
}

std::vector<MessageEntity> MessageDao::getMessages(UserEntity target, int time) {
	std::vector<MessageEntity> msgs;
	MessageEntity msg;
	sql::PreparedStatement* stmt = con->prepareStatement(
		std::string("SELECT user_id, username, content, time ") + 
		"FROM `chat_message` " +
		"JOIN `chat_user` on `chat_message`.user_id = `chat_user`.id WHERE target_id = ? AND `time` > ?"
	);
	stmt->setInt(1, target.getId());
	stmt->setInt(2, time);
	stmt->execute();
	sql::ResultSet* res = stmt->getResultSet();;
	UserEntity tempUser;
	while (res->next()) {
		msg.setId(res->getInt("user_id"));
		tempUser.setId(res->getInt("user_id"));
		tempUser.setUsername(res->getString("username"));
		msg.setUser(tempUser);
		msg.setTargetUser(target);
		msg.setContent(res->getString("content"));
		msg.setTime(res->getInt("time"));
		msgs.push_back(msg);
	}
	delete res;
	delete stmt;

	return msgs;
}

int MessageDao::saveMessage(MessageEntity msg) {
	int id;
	UserEntity user = msg.getUser();
	UserEntity target = msg.getTargetUser();
	if ((user.getId() > 0) && ((target.getId() > 0) ||(target.getId() == -1))) {
		sql::PreparedStatement* stmt = con->prepareStatement(
			std::string("INSERT INTO `chat_message`(`user_id`,`target_id`,`content`,`time`) ") +
			"VALUES (?,?,?,?)");
		stmt->setInt(1, user.getId());
		stmt->setInt(2, target.getId());
		stmt->setString(3, msg.getContent());
		stmt->setInt(4, msg.getTime());
		stmt->execute();
		id = last_insert_id();
		delete stmt;
	} else {
		id = -1;
	}
	return id;
}

std::vector<MessageEntity> MessageDao::getPublicMessages(int time) {
	return getMessages(UserEntity(GlobalClass::COMMON_USER_ID), time);
}


