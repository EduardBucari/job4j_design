create table recorder(
    id serial primary key,
    name varchar(100)
);

create table camera(
    id serial primary key,
    name varchar(100),
    recorder_id int references recorder(id)
);

