-- Use our assigned schema
USE local;

-- Drop each table individually if they exist
DROP TABLE IF EXISTS user cascade;
DROP TABLE IF EXISTS text_input cascade;


create table user
(
    user_type_admin BIT default false not null,
    user_id         BIGINT            not null primary key auto_increment,
    password        varchar(255)      not null,
    username        varchar(255)      not null
);

create table text_input
(
    text_id  BIGINT       not null primary key auto_increment,
    text     varchar(255) not null,
    username varchar(255) not null,
    time     bigint       not null
);

-- Create an admin user with the username 'admin' and password 'admin'
insert into user (user_type_admin, password, username)
values (true, '$2a$10$/AoHb24yYXa8WA9UTUESKukqVN4pI4.Rn0YFbtwEDbrkWCduVTGjC', 'admin');
