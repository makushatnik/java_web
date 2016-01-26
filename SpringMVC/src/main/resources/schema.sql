DROP TABLE IF EXISTS contact;

CREATE TABLE contact(
  id INT(3) NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(30) NOT NULL,
  last_name VARCHAR(30) NOT NULL,
  birth_date DATE,
  description VARCHAR(2000),
  photo BLOB,
  version INT(3) NOT NULL DEFAULT 0,
  UNIQUE uq_contact_1(first_name, last_name),
  PRIMARY KEY(id)
);