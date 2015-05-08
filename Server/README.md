#Server setup on Ubuntu 14.04

##Database setup

### Installing **mysql**

```
$ sudo apt-get install mysql-client-core-5.5
$ sudo apt-get install mysql-server
```
You will be asked to set a password for the **root** user, you shuld do this is highly recommended.
If everything went OK after typing the following :

```
$ mysql --vesion
```

You shoud get something similar to this:

```
mysql  Ver 14.14 Distrib 5.5.43, for debian-linux-gnu (x86_64) using readline 6.3
```

###Creating mysql user and database whit tabels:

For this we have written a MySQL script. You can find it unde *Server/Database/InstallDatabase.sql*.

> **Note:**
> Feel free to edit the created users name, password and the database name. But if you do **don't forget** to change this information in the *Server/GlobalClass.cpp* respectively.

To run the script type the following in the console
```
$ mysql -u root -p < Server/Database/InstallDatabase.sql
``` 

The script contains the following:
```
CREATE USER 'dbuser'@'localhost' IDENTIFIED BY 'dbpassword';
GRANT ALL ON chat_db.* TO 'dbuser'@'localhost';
CREATE DATABASE chat_db;
USE chat_db;
CREATE TABLE IF NOT EXISTS `chat_user` (
		`id` int(11) NOT NULL AUTO_INCREMENT,
		`username` varchar(32) COLLATE utf8_bin NOT NULL UNIQUE,
		`password` varchar(32) NOT NULL,
		`email` varchar(12) NOT NULL,
		`last_update` int(11) NOT NULL,
		PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `chat_message` (
		`id` int(11) NOT NULL AUTO_INCREMENT,
		`user_id` int(11) NOT NULL,
		`content` varchar(255)  COLLATE utf8_bin NOT NULL,
		`time` int(11) NOT NULL,
		`target_id` int(11) NOT NULL,
		PRIMARY KEY (`id`)
);
```

Then type the password you given at the installation process.
If you dont see any error messages evrything worked perfectly. 
***The database shoud be up and running.***



##Compiling and running the source

The Server uses automake for automatic make file generation.


###Install automake

For installing automake type the folloving command:

```
$ sudo apt-get install automake
```

###Install required library
The server uses the MySQL cpp connection library, enter the following to get it:
```
$ sudo apt-get install libmysqlcppconn-dev
```


##Compiling
To compile the Server type the following three lines of code:
```
$ autoreconf -i
$ ./configure
$ make
```
