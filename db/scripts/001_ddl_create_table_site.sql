create table site (
    id serial primary key not null,
    site varchar(2000) UNIQUE,
    login varchar(2000),
    password varchar(2000)
);