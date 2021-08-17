-- Заполняем таблицы начальными данными для системы заявок:
-- insert role
insert into role (name) values ('guest');
insert into role (name) values ('client');
insert into role (name) values ('administrator');
-- insert rules
insert into rules (name, read, write, delete)
values ('read', true, false, false);
insert into rules (name, read, write, delete)
values ('readWrite', true, true, false);
insert into rules (name, read, write, delete)
values ('readWriteDelete' true, true, true);

-- insert role_rules_id
insert into role_rules (role_id, rules_id) values (1, 1);
insert into role_rules (role_id, rules_id) values (2, 2);
insert into role_rules (role_id, rules_id) values (3, 3);

-- insert rules
insert into users (name, role_rules_id) values ('Peter Parker', 1);
insert into users (name, role_rules_id) values ('Tony Stark', 2);
insert into users (name, role_rules_id) values ('Bruce Wayne', 3);

-- insert category
insert into category (name) values ('smartphone');
insert into category (name) values ('pc');
insert into category (name) values ('notebook');

-- insert state
insert into state (name) values ('processing');
insert into state (name) values ('ready');

-- insert commentary
insert into commeentary (comment, users_id)
values ('java is my drug', 1);

-- insert attachment
insert into attachment (name, users_id)
values ('/user/path/file', 1);

-- insert new item
insert into item (name, users_id, category_id, state_id,
                  commentary_id, attachment_id)
values ('SAMSUNG GALAXY', 1, 1, 1, 1, 1);