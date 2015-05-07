#include "MessageDao.h"

MessageEntity MessageDao::getEntity(int id) {
	MessageEntity msg;
	
	sql::PreparedStatement* stmt = con->prepareStatement(
		"SELECT user_id, content, target_id, time FROM `chat_message` WHERE `id` = ?"
	);
	stmt->setInt(1, id);
	stmt->execute();
	sql::ResultSet* res = stmt->getResultSet();;
	UserEntity tempUser;
	if (res->next()) {
		msg.setId(id);
		tempUser.setId(res->getInt("user_id"));
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
		"SELECT id, user_id, content, time FROM `chat_message` WHERE target_id = ? AND `time` > ?"
	);
	stmt->setInt(1, target.getId());
	stmt->setInt(2, time);
	stmt->execute();
	sql::ResultSet* res = stmt->getResultSet();;
	UserEntity tempUser;
	if (res->next()) {
		msg.setId(res->getInt("user_id"));
		tempUser.setId(res->getInt("user_id"));
		msg.setTargetUser(tempUser);
		tempUser.setId(res->getInt("target_id"));
		msg.setUser(tempUser);
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