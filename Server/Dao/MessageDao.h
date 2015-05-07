#ifndef MESSAGEDAO_H
#define MESSAGEDAO_H

#include "Dao.h"
#include "../Entity/MessageEntity.h"
#include "../Entity/UserEntity.h"

class MessageDao : public Dao {
public:
	MessageEntity getEntity(int id);
	std::vector<MessageEntity> getMessages(UserEntity target, int time);
	int saveMessage(MessageEntity msg);
};

#endif