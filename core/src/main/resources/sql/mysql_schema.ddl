-- noinspection SqlDialectInspectionForFile

DROP TABLE ORDER_ITEM #
DROP TABLE PHONIFY_ORDER #
DROP TABLE PHONE #
DROP TABLE AUTHORITIES #
DROP TABLE USERS #

CREATE TABLE USERS (
  username VARCHAR(50) PRIMARY KEY NOT NULL ,
  password VARCHAR(60) NOT NULL ,
  enabled TINYINT NOT NULL
) #

CREATE TABLE AUTHORITIES (
  username  VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,

  FOREIGN KEY (username) REFERENCES USERS (username)
) #

CREATE UNIQUE INDEX IX_AUTH_USERNAME ON AUTHORITIES (username, authority) #

CREATE TABLE PHONE
(
  `KEY` BIGINT NOT NULL AUTO_INCREMENT,
  MODEL VARCHAR(40),
  COLOR VARCHAR(20),
  PRICE DECIMAL,

  PRIMARY KEY (`KEY`)
) #

CREATE TABLE PHONIFY_ORDER
(
  `KEY` BIGINT NOT NULL AUTO_INCREMENT,
  USERNAME VARCHAR(50),
  TOTAL_PRICE DECIMAL,
  SHIPPING_PRICE DECIMAL,
  FIRST_NAME VARCHAR(40),
  LAST_NAME VARCHAR(40),
  DELIVERY_ADDRESS VARCHAR(80),
  CONTACT_PHONE VARCHAR(20),
  DELIVERED TINYINT,

  PRIMARY KEY (`KEY`),
  FOREIGN KEY (USERNAME) REFERENCES USERS (username)
) #

CREATE TABLE ORDER_ITEM
(
  `KEY` BIGINT NOT NULL AUTO_INCREMENT,
  QUANTITY INT,
  PHONE_FK BIGINT,
  ORDER_FK BIGINT,

  PRIMARY KEY (`KEY`),
  FOREIGN KEY (ORDER_FK) REFERENCES PHONIFY_ORDER(`KEY`),
  FOREIGN KEY (PHONE_FK) REFERENCES PHONE(`KEY`)
) #
