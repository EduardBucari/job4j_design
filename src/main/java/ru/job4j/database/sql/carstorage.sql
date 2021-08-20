-- Хранилище машин.
-- Задание:
-- 1. Нужно написать SQL скрипты:
-- Создать структуру данных в базе.
-- Таблицы (Кузов. Двигатель, Коробка передач).
-- Создать структуру Машина. Машина не может существовать без данных из п.1.
-- Заполнить таблицы через insert.

-- 2. Создать SQL запросы:
--    1) Вывести список всех машин и все привязанные к ним детали.
--    2) Вывести отдельно детали (1 деталь - 1 запрос), которые
--       не используются НИ в одной машине, кузова, двигатели, коробки передач.

-- Создаем таблицы (Кузов. Двигатель, Коробка передач).
create table body (
  id serial primary key,
  name varchar(255)
);

create table engine (
  id serial primary key,
  name varchar(255)
);

create table transmission (
  id serial primary key,
  name varchar(255)
);

-- Создаём структуру Машина.
-- Машина не может существовать без Кузова, Двигателя, Коробки передач.
create table car (
  id serial primary key,
  name varchar(255),
  body_id int references body(id),
  engine_id int references engine(id),
  transmission_id int references transmission(id)
);

-- Заполням таблицы (Кузов. Двигатель, Коробка передач).
insert into body (name) values ('Hatch-back');
insert into body (name) values ('Sedan');
insert into body (name) values ('Universal');

insert into engine (name) values ('v4 disel');
insert into engine (name) values ('v6 disel');
insert into engine (name) values ('v8 disel');
insert into engine (name) values ('v8 turbo');

insert into transmission (name) values ('manual gearbox');
insert into transmission (name) values ('automat gearbox');
insert into transmission (name) values ('half-automat gearbox');

-- Заполняем структуру Машина.
insert into car (name, body_id, engine_id, transmission_id)
values ('BMW Z3', 1, 2, 2);
insert into car (name, body_id, engine_id, transmission_id)
values ('AUDI A6', 1, 1, 1);
insert into car (name, body_id, engine_id, transmission_id)
values ('FORD T6', 2, 3, 2);

-- 2. Создать SQL запросы:
--    1) Выводим список всех машин и все привязанные к ним детали.
select c.name, b.name, e.name, t.name
from car as c
  join body as b
      on c.body_id = b.id
  join engine as e
      on c.engine_id = e.id
  join transmission as t
      on c.transmission_id = t.id;

--    2) Вывести отдельно детали (1 деталь - 1 запрос),
--       которые не используются НИ в одной машине,
--       кузова, двигатели, коробки передач.
select b.name as "unused body"
from car as c
right join body as b
on c.body_id = b.id
where c.id is null;

select e.name as "unused engine"
from car as c
right join engine as e
on c.engine_id = e.id
where c.id is null;

select t.name as "unused transmission"
from transmission as t
left join car as c
on t.id = c.transmission_id
where c.id is null;