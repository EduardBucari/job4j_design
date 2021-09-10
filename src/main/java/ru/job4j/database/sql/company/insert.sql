-- TRUNCATE person CASCADE;
-- TRUNCATE company CASCADE;

insert into company
values (1, 'Company_1'),
       (2, 'Company_2'),
       (3, 'Company_3'),
       (4, 'Company_4'),
       (5, 'Company_5'),
       (6, 'Company_6'),
       (7, 'Company_7');

insert into person
values (1, 'Company_1', 1),
       (2, 'Company_2', 2),
       (3, 'Company_3', 3),
       (4, 'Company_4', 4),
       (5, 'Company_5', 5),
       (6, 'Company_6', 6),
       (7, 'Company_p', 6);