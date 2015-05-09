.PHONY: all clean

all: test

SRC = ../Entity/Entity.cpp ../Entity/UserEntity.cpp ../Entity/MessageEntity.cpp
SRC_TEST = Entity/UserEntity.cc  Entity/MessageEntity.cc 

OBJS = $(SRC:.cc=.o)
OBJS += $(SRC_TEST:.cc=.o)

CPPFLAGS = -Wall -pedantic
TESTLIBFLAGS = -lgtest -lgtest_main -lpthread

test: $(OBJS)
	g++ $(OBJS) -o test $(TESTLIBFLAGS)

%.o: %.cc
	g++ -c $(CPPFLAGS) $< -o $@

%.o: %.cpp
	../make

clean:
	rm -f *.o test Entity/*.o
