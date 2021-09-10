-- SQL Select by company.
-- Задание:
-- 1. В одном запросе получить
--    имена всех person, которые не состоят в компании с id = 5;
--    название компании для каждого человека.

-- 2. Необходимо выбрать название компании с максимальным количеством
--    человек + количество человек в этой компании.


-- Задание 1:
select c.id, c.name || ' : ' || p.name as info
from person p
     join company c on p.company_id = c.id
where c.id <> 5;

-- Задание 2:
select c.name, count(p.name)
from company c
     join person p on c.id = p.company_id
group by c.name
order by count(p.name) desc
limit 1;