-- Фильтры
-- В системе заданы таблицы:
-- product(id, name, type_id, expired_date, price)
-- type(id, name)

-- Задание.
-- 1. Написать запрос получение всех продуктов с типом "СЫР"
-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
-- 3. Написать запрос, который выводит все продукты, срок годности которых уже истек
-- 4. Написать запрос, который выводит самый дорогой продукт.
-- 5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих.
--    В виде имя_типа, количество
-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
--    Под количеством подразумевается количество продуктов определенного типа.
--    Например, если есть тип "СЫР" и продукты "Сыр плавленный" и "Сыр моцарелла",
--    которые ему принадлежат, то количество продуктов типа "СЫР" будет 2.
-- 8. Вывести все продукты и их тип.


-- Создаем таблицу product(id, name, type_id, expired_date, price)
create table product (
      id serial primary key,
      name varchar(255),
      type_id int,
      expired_date date,
      price int
);

-- Создаем таблицу type(id, name)
create table type (
     id serial primary key,
     name varchar(255)
);

-- заполняем таблицу type(name) название продуктов
insert into type (name) values ('СЫР');
insert into type (name) values ('МОЛОКО');
insert into type (name) values ('ХЛЕБ');
insert into type (name) values ('КОЛБАСА');
insert into type (name) values ('МОРОЖЕНОЕ');

-- заполняем таблицу product(name, type_id, expired_date, price)
-- заполняем таблицу product ('СЫР') type_id 1.
insert into product (name, type_id, expired_date, price)
	values ('Российский', 1, current_date + 10, 500);
insert into product (name, type_id, expired_date, price)
	values ('Пармезан', 1, current_date + 15, 1500);
insert into product (name, type_id, expired_date, price)
	values ('Моцарела', 1, current_date + 5, 800);
insert into product (name, type_id, expired_date, price)
	values ('Итальянский', 1, '2021.06.18', 750);

-- заполняем таблицу product ('МОЛОКО') type_id 2.
insert into product (name, type_id, expired_date, price)
	values ('Зеленое село', 2, current_date + 4, 50);
insert into product (name, type_id, expired_date, price)
	values ('Домик в деревне', 2, current_date + 8, 65);
insert into product (name, type_id, expired_date, price)
	values ('Козье', 2, current_date + 3, 80);

-- заполняем таблицу product ('ХЛЕБ') type_id 3.
insert into product (name, type_id, expired_date, price)
	values ('Пшеничный', 3, current_date + 3, 45);
insert into product (name, type_id, expired_date, price)
	values ('Бородинский', 3, current_date + 5, 65);
insert into product (name, type_id, expired_date, price)
	values ('Ржаной', 3, current_date + 5, 35);
insert into product (name, type_id, expired_date, price)
	values ('Цельнозерновой', 3, current_date + 6, 75);

-- заполняем таблицу product ('КОЛБАСА') type_id 4.
insert into product (name, type_id, expired_date, price)
	values ('Молочная', 4, current_date + 12, 350);
insert into product (name, type_id, expired_date, price)
	values ('Докторская', 4, current_date + 9, 450);
insert into product (name, type_id, expired_date, price)
	values ('Охотничья', 4, current_date + 14, 400);
insert into product (name, type_id, expired_date, price)
	values ('Сервелат', 4, current_date + 15, 650);
insert into product (name, type_id, expired_date, price)
	values ('Столичная', 4, '2021.06.15', 350);

-- заполняем таблицу product ('МОРОЖЕНОЕ') type_id 5.
insert into product (name, type_id, expired_date, price)
	values ('Мороженое Лакомка', 5, current_date + 15, 30);
insert into product (name, type_id, expired_date, price)
	values ('Мороженое Эскимо', 5, current_date + 10, 45);
insert into product (name, type_id, expired_date, price)
	values ('Мороженое Молочное', 5, current_date + 15, 40);
insert into product (name, type_id, expired_date, price)
	values ('Шоколадное', 5, current_date + 10, 55);

-- 1. Написать запрос получение всех продуктов с типом "СЫР"
select *
     from product as p
     join type as t
     on p.type_id = t.id
     where t.name = 'СЫР';

-- 2. Написать запрос получения всех продуктов,
--    у кого в имени есть слово "мороженое"
select *
     from product as p
     join type as t
     on p.type_id = t.id
     where p.name like '%Мороженое%';

-- 3. Написать запрос, который выводит все продукты,
--    срок годности которых уже истек
select p.name, p.expired_date, t.name
     from product as p
     join type as t
     on p.type_id = t.id
     where expired_date < current_date;

-- 4. Написать запрос, который выводит самый дорогой продукт.
select p.name, t.name, max(p.price) as m
     from product as p
     join type as t
     on p.type_id = t.id
     group by p.name, t.name
     order by m desc
     limit 1;

-- 5. Написать запрос, который выводит для каждого типа количество
--    продуктов к нему принадлежащих. В виде имя_типа, количество.
select t.name, count(p.type_id)
      from product as p
      join type as t
      on p.type_id = t.id
      group by t.name;

-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО".
select  p.name, t.name
     from product as p
     join type as t
     on p.type_id = t.id
     group by p.name, t.name
     having t.name = 'СЫР' or t.name = 'МОЛОКО'
     order by t.name asc;

-- 7. Написать запрос, который выводит тип продуктов,
--    которых осталось меньше 10 штук.
--    (в случае нашей базы данных - условие от 5 и больше)
--    Под количеством подразумевается количество продуктов
--    определенного типа.
--    Например, если есть тип "СЫР" и продукты "Сыр плавленный"
--    и "Сыр моцарелла", которые ему принадлежат,
--    то количество продуктов типа "СЫР" будет 2.
select t.name, count(t.name)
     from product as p
     join type as t
     on p.type_id = t.id
     group by t.name
     having count (t.name) >= 5;

--  8. Вывести все продукты и их тип.
select p.name, t.name
     from product as p
     join type as t
     on p.type_id = t.id;