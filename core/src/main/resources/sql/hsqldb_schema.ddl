-- noinspection SqlDialectInspectionForFile

DROP SCHEMA PUBLIC CASCADE

CREATE TABLE PHONIFY_USERS (
  username VARCHAR(50) PRIMARY KEY NOT NULL ,
  password VARCHAR(60) NOT NULL ,
  enabled TINYINT NOT NULL
) #

CREATE TABLE PHONIFY_AUTHORITIES (
  username VARCHAR(50) NOT NULL ,
  authority VARCHAR(50) NOT NULL ,

  FOREIGN KEY (username) REFERENCES PHONIFY_USERS (username)
) #

CREATE UNIQUE INDEX IX_AUTH_USERNAME ON PHONIFY_AUTHORITIES (username, authority) #

CREATE TABLE PHONE
(
  ID BIGINT IDENTITY PRIMARY KEY NOT NULL,
  MODEL VARCHAR(40),
  COLOR VARCHAR(20),
  PRICE DECIMAL
) #

CREATE TABLE PHONIFY_ORDER
(
  ID BIGINT IDENTITY PRIMARY KEY NOT NULL,
  USERNAME VARCHAR(50),
  TOTAL_PRICE DECIMAL,
  SHIPPING_PRICE DECIMAL,
  FIRST_NAME VARCHAR(40),
  LAST_NAME VARCHAR(40),
  DELIVERY_ADDRESS VARCHAR(80),
  CONTACT_PHONE VARCHAR(20),
  DELIVERED TINYINT,

  FOREIGN KEY (USERNAME) REFERENCES PHONIFY_USERS (username)
) #

CREATE TABLE ORDER_ITEM
(
  ID BIGINT IDENTITY PRIMARY KEY NOT NULL,
  QUANTITY INTEGER,
  PHONE_FK BIGINT,
  ORDER_FK BIGINT,

  FOREIGN KEY (ORDER_FK) REFERENCES PHONIFY_ORDER(ID),
  FOREIGN KEY (PHONE_FK) REFERENCES PHONE(ID)
) #
