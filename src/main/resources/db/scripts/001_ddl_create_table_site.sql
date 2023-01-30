create table site (
    id serial primary key not null,
    site varchar UNIQUE,
    login varchar,
    password varchar
);