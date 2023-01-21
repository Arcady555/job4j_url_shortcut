create table url_code (
    id serial primary key not null,
    url varchar(2000) UNIQUE,
    code varchar(2000) UNIQUE,
    total int
);