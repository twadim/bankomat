CREATE TABLE IF NOT EXISTS users
(
    id    SERIAL PRIMARY KEY ,
    name  VARCHAR(200) NOT NULL ,
    card CHAR(18) NOT NULL ,
    password CHAR(18)  NOT NULL
);