create table cars(
     id serial primary key,
     model varchar(100)
 );

 create table drivers(
     id serial primary key,
     name varchar(100),
     license varchar(30)
 );

 create table cars_drivers(
     id serial primary key,
     car_id int references cars(id),
     driver_id int references drivers(id)
 );