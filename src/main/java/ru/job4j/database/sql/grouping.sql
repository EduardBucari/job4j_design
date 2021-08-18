--  Группировка и агрегатные функции.
-- Есть готовые таблицы.
-- Задание:
-- 1. Заполнить таблицы данными.
-- 2. Используя агрегатные функции вывести среднюю цену устройств.
-- 3. Используя группировку вывести для каждого человека среднюю цену его устройств.
-- 4. Дополнить запрос п.3. условием, что средняя стоимость устройств должна быть больше 5000.

create table devices (
      id serial primary key,
      name varchar(255),
      price float
);

create table people (
      id serial primary key,
      name varchar(255)
);

create table devices_people (
      id serial primary key,
      device_id int references devices(id),
      people_id int references people(id)
);

-- заполняем таблицу "devices" (название товара и его стоимость)
insert into devices (name, price) values ('IpadPro 13', 4000);
insert into devices (name, price) values ('IpadPro 15', 4300);
insert into devices (name, price) values ('IpadPro 17', 4500);
insert into devices (name, price) values ('IpadMini 13', 4800);
insert into devices (name, price) values ('IpadMini 15', 5000);
insert into devices (name, price) values ('IpadMini 17', 5100);
insert into devices (name, price) values ('IpadMax 13', 5200);
insert into devices (name, price) values ('IpadMax 15', 5300);
insert into devices (name, price) values ('IpadMax 17', 5400);
insert into devices (name, price) values ('IpadMax 20', 5500);

-- заполняем таблицу "people" (имя)
insert into people (name) values ('Mike');
insert into people (name) values ('Antony');
insert into people (name) values ('Bread');
insert into people (name) values ('David');
insert into people (name) values ('Max');

-- заполняем таблицу "devices_people" (id товара и id человека)
insert into devices_people (device_id, people_id) values (10, 3);
insert into devices_people (device_id, people_id) values (9, 5);
insert into devices_people (device_id, people_id) values (8, 2);
insert into devices_people (device_id, people_id) values (7, 4);
insert into devices_people (device_id, people_id) values (6, 3);
insert into devices_people (device_id, people_id) values (5, 1);
insert into devices_people (device_id, people_id) values (4, 4);
insert into devices_people (device_id, people_id) values (3, 2);
insert into devices_people (device_id, people_id) values (2, 1);
insert into devices_people (device_id, people_id) values (1, 5);

-- Используя агрегатные функции выводим среднюю цену устройств.
select avg(price) from devices;

-- Используя группировку выводим для каждого человека среднюю цену его устройств.
select p.name, avg(d.price) from devices_people as dp
       join people as p on dp.people_id = p.id
       join devices as d on dp.device_id = d.id
group by p.name;

-- Используя группировку вывести для каждого человека среднюю цену его устройств.
-- Дополнительное условие - средняя стоимость устройств должна быть больше 5000.
select p.name, avg(d.price) from devices_people as dp
       join people as p on dp.people_id = p.id
       join devices as d on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;