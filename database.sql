#MySQL
#create tabel
CREATE TABLE student(
  id INT(30) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL ,
  age INT(4) NOT NULL ,
  grade INT(2) NOT NULL ,
  sex VARCHAR(8) NOT NULL
);
DESC student;
