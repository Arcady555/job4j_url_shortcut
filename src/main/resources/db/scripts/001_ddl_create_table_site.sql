create table if not exists  site (
    id serial primary key not null,
    site varchar UNIQUE,
    login varchar,
    password varchar
);