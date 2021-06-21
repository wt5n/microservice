--changeset vladimir.v - 1
create table users
(
    user_id    serial unique primary key not null,
    username   varchar(40) unique        not null,
    password   varchar(255)              not null,
    is_enabled boolean                   not null
--     role       text
);