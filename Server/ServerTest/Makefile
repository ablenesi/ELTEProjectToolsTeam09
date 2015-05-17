.PHONY: all clean

all: test

SRC = ../GlobalClass.cpp ../Server/ChatHandler.cpp ../Server/Server.cpp ../Entity/Entity.cpp ../Entity/UserEntity.cpp ../Entity/MessageEntity.cpp ../Dao/Dao.cpp ../Dao/UserDao.cpp ../Dao/MessageDao.cpp

SRC_TEST = GlobalClass.cc Entity/UserEntity.cc  Entity/MessageEntity.cc 

OBJS = $(SRC:.cc=.o)
OBJS += $(SRC_TEST:.cc=.o)

CPPFLAGS = -Wall -std=gnu++0x
TESTLIBFLAGS = -lgtest -lgtest_main -lpthread -lmysqlcppconn

test: $(OBJS)
	g++ $(CPPFLAGS) $(OBJS) -o test $(TESTLIBFLAGS)

%.o: %.cc
	g++ -c $(CPPFLAGS) $< -o $@

%.o: %.cpp
	../make

clean:
	rm -f *.o test Entity/*.o
