DROP TABLE users IF EXISTS;

CREATE TABLE users
(
    uid INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 100, INCREMENT BY 1) PRIMARY KEY,
    name varchar(100) NOT NULL,
    email varchar(100) DEFAULT NULL
);

insert into users(name, email) 
values('Jack','jack@abc.com'),
('Ryan','ryan@abc.com'),
('Leon','leon@abc.com');