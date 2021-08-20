-- outer join
-- Задание:
-- Даны две сущности, представленные в таблицах departments и employees.
-- Отношение one-to-many. В таблицах только поле name.
-- 1. Создать таблицы и заполнить их начальными данными.
-- 2. Выполнить запросы с left, right, full, cross соединениями.
-- 3. Используя left join найти департаменты, у которых нет работников.
-- 4. Используя left и right join написать запросы, которые давали бы одинаковый результат.
-- 5. Создать таблицу teens с атрибутами name, gender и заполнить ее.
--    Используя cross join составить все возможные разнополые пары.

-- 1 Создпем таблицы departments и employees.
--   Отношение one-to-many. В таблицах только поле name.
create table departments(
      id serial primary key,
      name varchar(255)
);

create table employees(
      id serial primary key,
      name varchar(255),
      department_id int references departments(id)
);

-- Заполняем таблицу departments (название департаментов).
insert into departments (name) values ('production department');
insert into departments (name) values ('accounts department');
insert into departments (name) values ('advertizing department');
insert into departments (name) values ('sales department');
insert into departments (name) values ('transportation department');

-- Заполняем таблицу employees (имена работников и id департамента).
insert into employees (name, department_id) values ('Harry', 5);
insert into employees (name, department_id) values ('Oliver', 4);
insert into employees (name, department_id) values ('Jack', 5);
insert into employees (name, department_id) values ('Charlie', 2);
insert into employees (name, department_id) values ('Thomas', 1);
insert into employees (name, department_id) values ('Jacob', 5);
insert into employees (name, department_id) values ('William', 1);
insert into employees (name, department_id) values ('Jessica', 4);
insert into employees (name, department_id) values ('Emily ', 5);
insert into employees (name, department_id) values ('Isabella', 2);


-- 2 Выполняем запросы с left, right, full, cross соединениями.
-- left join
-- В данном случае «внешней» будет таблица departments.
-- Следовательно, выбираются записи из departments
-- и для них подбираются записи в таблице employees.
-- т.е. получим записи из departments, только с присоединенными записями из employees .
select *
from departments as d
left join employees as e
on d.id = e.department_id;

-- right join
-- при правом соединении выбираются записи из правой таблицы employees
-- и для них подбираются записи из левой таблицы departments, которые удовлетворяют условию.
select *
from departments as d
right join employees as e
on d.id = e.department_id;

-- full join
-- Данный тип внешнего соединения дает результат левого + правого соединений,
-- т.е. представляет собой комбинацию этих двух соединений.
-- Выполняется левое соединение, выполняется правое соединение
-- и оба результата этих запросов попадают в результирующую выборку.
select *
from employees as e
left join departments as d
on department_id = d.id;

select *
from departments as d
right join employees as e
on d.id = e.department_id;

select *
from employees as e
full join departments as d
on department_id = d.id;

-- cross join
-- Результатом этого запроса является декартово множество,
-- т.е. все пары элементов.
select *
from employees as e
cross join departments as d;


-- 3. Используя left join найти департаменты, у которых нет работников.
select d.name
from departments as d
left join employees as e
on d.id = e.department_id
where e.name is null ;

-- 4. Используя left и right join написать запросы,
--    которые давали бы одинаковый результат.
select *
from employees as e
left join departments as d
on department_id = d.id;

select *
from employees as e
right join departments as d
on department_id = d.id
where e.name is not null;

-- 5. Создать таблицу teens с атрибутами name, gender и заполнить ее.
--    Используя cross join составить все возможные разнополые пары.
create table teens (
      id serial primary key,
      name varchar(255),
      gender varchar(255)
);

insert into teens (name, gender) values ('Luisa', 'female');
insert into teens (name, gender) values ('Lucas', 'male');
insert into teens (name, gender) values ('Eva', 'female');
insert into teens (name, gender) values ('David', 'male');

select
t1.name as one,
t2.name as two
from teens t1
cross join teens t2
where t1.gender != t2.gender;