create table if not exists client (
    id serial primary key,
    name varchar(255) not null,
    cpf varchar(255) unique not null,
    birth_date date not null,
    street varchar(255) not null,
    number varchar(255) not null,
    complement varchar(255),
    zip_code varchar(255) not null,
    city varchar(255) not null,
    is_active boolean not null default true
);