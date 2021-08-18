-- Inner join.
-- Задание:
-- 1. Придумать две таблицы и связь между ними.
-- 2. Написать 3 запроса с inner join с использованием альясов.

-- Создаем две таблицы и связь между ними (many to one)
create table itprofession (
      id serial primary key,
      name varchar(255)
);
create table developer (
      id serial primary key,
      name varchar(255),
      itprofession_id int references itprofession(id)
);

-- заполняем таблицу "it profession"
insert into itprofession (name) values ('java developer');
insert into itprofession (name) values ('python developer');
insert into itprofession (name) values ('js developer');

-- заполняем таблицу "developer"
insert into developer (name, itprofession_id) values ('Mike', 2);
insert into developer (name, itprofession_id) values ('David', 1);
insert into developer (name, itprofession_id) values ('Eduard', 1);
insert into developer (name, itprofession_id) values ('Tony', 3);

-- делаем 3 запроса с inner join с использованием альясов (Для сокращений).
select d.id, d.name, i.name from developer as d join itprofession as i on d.itprofession_id = i.id where i.name = 'java develor';
select * from developer as d join itprofession as i on d.itprofession_id = i.id;
select d.name as "Developer name is ", i.name as "IT Speciality is " from developer as d join itprofession as i on d.itprofession_id = i.id;
