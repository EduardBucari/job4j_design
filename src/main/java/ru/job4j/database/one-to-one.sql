create table diplom(
    id serial primary key,
    seria int,
    number int
);

create table person(
    id serial primary key,
    name varchar(100),
    diplom_id int references diplom(id) unique
);