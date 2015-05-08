
bin_PROGRAMS = server


server_SOURCES=main.cpp GlobalClass.cpp Server/ChatHandler.cpp Server/Server.cpp Entity/Entity.cpp Entity/UserEntity.cpp Entity/MessageEntity.cpp Dao/Dao.cpp Dao/UserDao.cpp Dao/MessageDao.cpp


noinst_HEADERS= GlobalClass.h Server/ChatHandler.h Server/Server.h Entity/Entity.h Entity/UserEntity.h Entity/MessageEntity.h Dao/Dao.h Dao/UserDao.h Dao/MessageDao.h

server_LDADD = -lmysqlcppconn 
