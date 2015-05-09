#include "gmock/gmock.h"
#include "../../Entity/UserEntity.h"
#include "../../Entity/MessageEntity.h"


TEST(MessageEntity, testGetterSetter) {

	MessageEntity messageentity;
	UserEntity testUser(3);
	messageentity.setId(13);
	messageentity.setContent("This is the test content");
	messageentity.setTime(88);
	messageentity.setUser(testUser);
	messageentity.setTargetUser(testUser);

	EXPECT_EQ(13, messageentity.getId());
	EXPECT_STREQ("This is the test content", messageentity.getContent().c_str());
	EXPECT_EQ(88, messageentity.getTime());
	EXPECT_EQ(3, messageentity.getUser().getId());
 	EXPECT_EQ(3, messageentity.getTargetUser().getId());

}