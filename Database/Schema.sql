-- Use our assigned schema
USE local;

-- disable foreign key checks so that tables could be droppped
SET FOREIGN_KEY_CHECKS = 0;

-- Drop each table individually if they exist
DROP TABLE IF EXISTS user cascade;
DROP TABLE IF EXISTS text_input cascade;

-- re-enable foreign key checks for normal functionality
SET FOREIGN_KEY_CHECKS = 1;

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

