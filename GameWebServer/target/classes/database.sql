CREATE TABLE users(
  id INT(3) NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  pass VARCHAR(30),
  email VARCHAR(30),
  admin INT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY(id));

INSERT INTO users VALUES('', 'john', 's3cret', 'admin@jettygame.com', 1);