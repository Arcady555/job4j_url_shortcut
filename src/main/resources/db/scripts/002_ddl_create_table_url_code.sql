create table url_code (
    id serial primary key not null,
    url varchar UNIQUE,
    code varchar UNIQUE,
    total int default 0
);