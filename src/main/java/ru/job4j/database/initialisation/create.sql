-- Создаем основные и добавочные таблицы для хранения структуры системы заявок.
-- В системе должны существовать:
-- Пользователи. Роли. Права ролей. Заявки. Комментарии Заявок. Приложенные Файлы. Состояние заявки. Категории заявки.

-- Роли
create table role (
      id serial primary key,
      name varchar(100)
);

-- Права ролей
create table rules (
      id serial primary key,
      name varchar(100),
      read boolean,
      write boolean,
      delete boolean
);

-- Связи между таблицами:
-- role - rules = many-to-many
create table role_rules (
      id serial primary key,
      role_id int references role(id),
      rules_id int references rules(id)
);

-- Пользователи
-- Связи между таблицами:
-- users - role = many-to-one
create table users (
      id serial primary key,
      name varchar(100),
      role_id int references role(id)
);

-- Категории заявки
create table cartegory (
      id serial primary key,
      name varchar(100)
);

-- Состояние заявки
create table state (
      id serial primary key,
      name varchar(100)
);

--Комментарии Заявок.
create table commentary (
      id serial primary key,
      comment varchar(1500),
      users_id int references users(id),
      item_id int references item(id)
);

-- Приложенные Файлы.
create table attachment (
      id serial primary key,
      name varchar(100),
      users_id int references users(id),
      item_id int references item(id)
);

-- Заявки.
ceate table item (
     id serial primary key,
     name varchar(200),
     users_id int references users(id),           -- item - users = many-to-one
     category_id int references category(id),     -- item - category = many-to-one
     state_id int references state(id)            -- item - state = many-to-one
);