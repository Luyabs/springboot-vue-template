create table book
(
    id          int auto_increment primary key,
    type        varchar(20)                          not null,
    name        varchar(40)                          not null,
    description varchar(80) default 'no description' null,
    create_time datetime                             not null,
    update_time datetime                             not null
);


create table user
(
    id       int auto_increment primary key,
    username varchar(20) not null,
    password varchar(20) null,
    birthday date        not null
);

