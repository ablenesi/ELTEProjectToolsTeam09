#Client setup on Ubuntu 14.04

##Compiling and running the source

The Client uses maven project management and comprehension tool.

###Install maven
For installing automake type the folloving command:
```
$ sudo apt-get install maven
```

##Compiling
To compile the Client type the following three lines of code:
```
$ mvn package
```

##Running
To run the application after compiling go to ```Client/target``` then run it using the following code:
```
java -jar ChatClient-0.0.1-SNAPSHOT.jar
```
