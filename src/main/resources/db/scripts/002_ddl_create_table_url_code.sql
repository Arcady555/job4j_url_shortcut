create table if not exists url_code (
    id serial primary key not null,
    url varchar UNIQUE,
    code varchar UNIQUE,
    total int default 0
);