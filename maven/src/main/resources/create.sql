CREATE TABLE accounts(
  id INT(3) NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(20) NOT NULL,
  last_name VARCHAR(20) NOT NULL,
  age INT(2) NOT NULL,
  PRIMARY KEY(id)
);

INSERT INTO accounts(first_name, last_name, age) VALUES('John', 'Smith', 36);
INSERT INTO accounts(first_name, last_name, age) VALUES('Chris', 'Schaefer', 27);
INSERT INTO accounts(first_name, last_name, age) VALUES('Scott', 'Tiger', 32);
