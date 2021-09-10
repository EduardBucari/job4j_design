-- Даны 2 следующих скрипта, которые создают таблицы в БД:
-- 1. Таблица company
-- 2. Таблица person

drop table if exists person cascade;
drop table if exists company cascade;

-- 1. Таблица company
create table if not exists company
(
   id int not null,
   name varchar,
   constraint compant_pk primary key(id)
);

-- 2. Таблица person
create table if not exists person
(
   id integer not null,
   name varchar,
   company_id int references company (id),
   constraint person_pk primary key (id)
);