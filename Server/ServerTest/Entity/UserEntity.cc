#include "gmock/gmock.h"
#include "../../Entity/UserEntity.h"

TEST(UserEntity, testGetterSetter) {

	UserEntity userEntity;
	userEntity.setId(22);
	userEntity.setUsername("TestUsername");
	userEntity.setPassword("TestPassword");
	userEntity.setEmail("TestEmail@gmail.com");
	userEntity.setLastUpdate(888);

	EXPECT_EQ(22, userEntity.getId());
	EXPECT_STREQ("TestUsername", userEntity.getUsername().c_str());
	EXPECT_STREQ("TestPassword", userEntity.getPassword().c_str());
	EXPECT_STREQ("TestEmail@gmail.com", userEntity.getEmail().c_str());
 	EXPECT_EQ(888, userEntity.getLastUpdate());

}

