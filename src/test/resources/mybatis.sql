DROP DATABASE IF EXISTS mybatis;
CREATE DATABASE mybatis DEFAULT CHARACTER SET utf8;

use mybatis;
CREATE TABLE student(
  id int(11) NOT NULL AUTO_INCREMENT,
  studentID int(11) NOT NULL UNIQUE,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO student VALUES(1,1,'我没有三颗心脏');
INSERT INTO student VALUES(2,2,'我没有三颗心脏');
INSERT INTO student VALUES(3,3,'我没有三颗心脏');