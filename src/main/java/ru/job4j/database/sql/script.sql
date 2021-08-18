create table worker(
	id serial primary key,
	name varchar(50),
	birthday date,
	married boolean
);
insert into worker(name, birthday, married) values('David', '2001.05.06', true);
update worker set name = 'Alex';
delete from worker;

select * from worker;