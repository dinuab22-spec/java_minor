# java_minor
project


//installed mariadb database


sudo apt install default-mysql-server


//creating a user name oop and pass ----------

CREATE USER 'oop'@'localhost' IDENTIFIED BY 'dinu';
GRANT ALL PRIVILEGES ON *.* TO 'oop'@'localhost';
FLUSH PRIVILEGES;
EXIT;


//driver for maria and mysql debian(linux)

sudo apt install libmariadb-java
