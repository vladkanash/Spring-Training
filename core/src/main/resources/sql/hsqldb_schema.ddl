-- noinspection SqlDialectInspectionForFile

DROP SCHEMA PUBLIC CASCADE

CREATE TABLE PHONE
(
  KEY BIGINT IDENTITY PRIMARY KEY NOT NULL,
  MODEL VARCHAR(40),
  COLOR VARCHAR(20),
  PRICE DECIMAL
) #

CREATE TABLE PHONIFY_ORDER
(
  KEY BIGINT IDENTITY PRIMARY KEY NOT NULL,
  TOTAL_PRICE DECIMAL,
  SHIPPING_PRICE DECIMAL,
  FIRST_NAME VARCHAR(40),
  LAST_NAME VARCHAR(40),
  DELIVERY_ADDRESS VARCHAR(80),
  CONTACT_PHONE VARCHAR(20),
  DELIVERED TINYINT
) #

CREATE TABLE ORDER_ITEM
(
  KEY BIGINT IDENTITY PRIMARY KEY NOT NULL,
  QUANTITY INTEGER,
  PHONE_FK BIGINT,
  ORDER_FK BIGINT,

  FOREIGN KEY (ORDER_FK) REFERENCES PHONIFY_ORDER(KEY),
  FOREIGN KEY (PHONE_FK) REFERENCES PHONE(KEY)
) #

CREATE TABLE USERS (
  username VARCHAR(50) PRIMARY KEY NOT NULL ,
  password VARCHAR(60) NOT NULL ,
  enabled TINYINT NOT NULL
) #

CREATE TABLE AUTHORITIES (
  username VARCHAR(50) NOT NULL ,
  authority VARCHAR(50) NOT NULL ,

  FOREIGN KEY (username) REFERENCES USERS (username)
) #

CREATE UNIQUE INDEX IX_AUTH_USERNAME ON AUTHORITIES (username, authority) #
