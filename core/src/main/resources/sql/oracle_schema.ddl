-- noinspection SqlDialectInspectionForFile
-- Generated by Oracle SQL Developer Data Modeler 4.1.5.907
--   at:        2016-11-14 19:47:58 MSK
--   site:      Oracle Database 11g
--   type:      Oracle Database 11g

CREATE TABLE USERS (
  username VARCHAR(50) NOT NULL ,
  password VARCHAR(60) NOT NULL ,
  enabled NUMBER(1) NOT NULL
) #
ALTER TABLE USERS ADD CONSTRAINT USERS_PK PRIMARY KEY ( username ) #

CREATE TABLE AUTHORITIES (
  username VARCHAR(50) NOT NULL ,
  authority VARCHAR(50) NOT NULL
) #
ALTER TABLE AUTHORITIES ADD CONSTRAINT AUTHORITIES_USERS_FK FOREIGN KEY ( username ) REFERENCES USERS ( username ) #
CREATE UNIQUE INDEX IX_AUTH_USERNAME ON AUTHORITIES (username, authority) #


CREATE TABLE PHONIFY_ORDER
  (
    KEY                  NUMBER(19) ,
    USERNAME             VARCHAR(50) ,
    TOTAL_PRICE          NUMBER ,
    SHIPPING_PRICE       NUMBER ,
    FIRST_NAME           VARCHAR2 (40 CHAR) ,
    LAST_NAME            VARCHAR2 (40 CHAR) ,
    DELIVERY_ADDRESS     VARCHAR2 (40 CHAR) ,
    CONTACT_PHONE        VARCHAR2 (20 CHAR) ,
    DELIVERED            NUMBER(1)
  ) #
ALTER TABLE PHONIFY_ORDER ADD CONSTRAINT ORDER_PK PRIMARY KEY ( KEY ) #
ALTER TABLE PHONIFY_ORDER ADD CONSTRAINT ORDER_USER_FK FOREIGN KEY ( USERNAME ) REFERENCES USERS ( username ) #

CREATE TABLE ORDER_ITEM
  (
    KEY       NUMBER(19) ,
    QUANTITY  INTEGER ,
    ORDER_FK INTEGER ,
    PHONE_FK INTEGER
  ) #
ALTER TABLE ORDER_ITEM ADD CONSTRAINT ORDER_ITEM_PK PRIMARY KEY ( KEY ) #


CREATE TABLE PHONE
  (
    KEY   NUMBER(19) ,
    MODEL VARCHAR2 (40 CHAR) ,
    PRICE NUMBER ,
    COLOR VARCHAR2 (20 CHAR)
  ) #
ALTER TABLE PHONE ADD CONSTRAINT PHONE_PK PRIMARY KEY ( KEY ) #


ALTER TABLE ORDER_ITEM ADD CONSTRAINT ORDER_ITEM_ORDER_FK FOREIGN KEY ( ORDER_FK ) REFERENCES PHONIFY_ORDER ( KEY ) #
ALTER TABLE ORDER_ITEM ADD CONSTRAINT ORDER_ITEM_PHONE_FK FOREIGN KEY ( PHONE_FK ) REFERENCES PHONE ( KEY ) #

CREATE SEQUENCE PHONIFY_ORDER_KEY_SEQ START WITH 1 NOCACHE ORDER #
CREATE OR REPLACE TRIGGER PHONIFY_ORDER_KEY_TRG BEFORE
INSERT ON PHONIFY_ORDER FOR EACH ROW WHEN (NEW.KEY IS NULL) BEGIN :NEW.KEY := PHONIFY_ORDER_KEY_SEQ.NEXTVAL;
END;
#

CREATE SEQUENCE ORDER_ITEM_KEY_SEQ START WITH 1 NOCACHE ORDER #
CREATE OR REPLACE TRIGGER ORDER_ITEM_KEY_TRG BEFORE
INSERT ON ORDER_ITEM FOR EACH ROW WHEN (NEW.KEY IS NULL) BEGIN :NEW.KEY := ORDER_ITEM_KEY_SEQ.NEXTVAL;
END;
#

CREATE SEQUENCE PHONE_KEY_SEQ START WITH 1 NOCACHE ORDER #
CREATE OR REPLACE TRIGGER PHONE_KEY_TRG BEFORE
INSERT ON PHONE FOR EACH ROW WHEN (NEW.KEY IS NULL) BEGIN :NEW.KEY := PHONE_KEY_SEQ.NEXTVAL;
END;
#

ALTER TRIGGER PHONE_KEY_TRG ENABLE#
ALTER TRIGGER PHONIFY_ORDER_KEY_TRG ENABLE#
ALTER TRIGGER ORDER_ITEM_KEY_TRG ENABLE#


