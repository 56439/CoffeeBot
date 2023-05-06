DROP TABLE IF EXISTS ORDERS;
DROP TABLE IF EXISTS DRINK_VOLUMES;
DROP TABLE IF EXISTS DRINKS;
DROP TABLE IF EXISTS CAFE;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS ADMIN_ROLE;
DROP TABLE IF EXISTS ADMINS;

CREATE TABLE DRINKS (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
    "name" VARCHAR(255) NOT NULL,
    DESCRIPTION VARCHAR(255) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE DRINK_VOLUMES (
    DRINK_ID BIGINT NOT NULL,
    VOLUME VARCHAR(255) NOT NULL,
    PRICE INTEGER,
    FOREIGN KEY (DRINK_ID) REFERENCES DRINKS (ID)
);

CREATE TABLE CAFE (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
    ADDRESS VARCHAR(255) NOT NULL UNIQUE,
    COORDINATES VARCHAR(255) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE USERS (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
    CHAT_ID BIGINT NOT NULL UNIQUE,
    "name" VARCHAR(255) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE ORDERS (
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
    DRINK_ID BIGINT NOT NULL,
    VOLUME INTEGER NOT NULL,
    USER_ID BIGINT NOT NULL,
    CAFE_ID BIGINT NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (DRINK_ID) REFERENCES DRINKS (ID) on delete cascade,
    FOREIGN KEY (USER_ID) REFERENCES USERS (ID),
    FOREIGN KEY (CAFE_ID) REFERENCES CAFE (ID) on delete cascade
);

CREATE TABLE ADMINS(
    ID BIGINT GENERATED BY DEFAULT AS IDENTITY,
    USERNAME VARCHAR(255) NOT NULL UNIQUE,
    PASSWORD VARCHAR(255) NOT NULL,
    PRIMARY KEY (ID)
);

CREATE TABLE ADMIN_ROLE(
  ADMIN_ID BIGINT not null,
  ROLES VARCHAR(255),
  FOREIGN KEY (ADMIN_ID) REFERENCES ADMINS (ID) ON DELETE CASCADE
);