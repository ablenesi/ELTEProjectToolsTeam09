#include "gmock/gmock.h"
#include "../GlobalClass.h"
#include <thread>

TEST(GlobalClass, trimTest) {
	
	EXPECT_STREQ(GlobalClass::trim("").c_str(), "");
	EXPECT_STREQ(GlobalClass::trim("test").c_str(), "test");
	EXPECT_STREQ(GlobalClass::trim(" ").c_str(), "");
	EXPECT_STREQ(GlobalClass::trim("	\n").c_str(), "");
	EXPECT_STREQ(GlobalClass::trim("	test\n").c_str(), "test");
	EXPECT_STREQ(GlobalClass::trim("test\n").c_str(), "test");
	EXPECT_STREQ(GlobalClass::trim("test ").c_str(), "test");
	EXPECT_STREQ(GlobalClass::trim(" test ").c_str(), "test");
	EXPECT_STREQ(GlobalClass::trim("	test	").c_str(), "test");
}

TEST(GlobalClass, splitTest) {
	std::string testStr = "1,2,3,4,5,6";
	std::vector<std::string> v = GlobalClass::split(testStr, ',');
	
	EXPECT_STREQ(v[0].c_str(), "1");
	EXPECT_STREQ(v[1].c_str(), "2");
	EXPECT_STREQ(v[2].c_str(), "3");
	EXPECT_STREQ(v[3].c_str(), "4");
	EXPECT_STREQ(v[4].c_str(), "5");
	EXPECT_STREQ(v[5].c_str(), "6");
}

TEST(GlobalClass, converterTest) {
	EXPECT_STREQ(GlobalClass::converter(1).c_str(), "1");
	EXPECT_STREQ(GlobalClass::converter(0).c_str(), "0");
	EXPECT_STREQ(GlobalClass::converter(91).c_str(), "91");
	EXPECT_STREQ(GlobalClass::converter(133).c_str(), "133");
	EXPECT_STREQ(GlobalClass::converter(-133).c_str(), "-133");
}


TEST(GlobalClass, timeTest) {
	long time1 = GlobalClass::currentTimeMillis();
	std::this_thread::sleep_for(std::chrono::milliseconds(1));
	long time2 = GlobalClass::currentTimeMillis();
	EXPECT_LT(time1, time2);
}
