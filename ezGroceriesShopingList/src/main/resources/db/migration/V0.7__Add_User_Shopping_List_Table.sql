alter table shopping_list add column username varchar(50);
alter table users drop column password ;
alter table users add column password varchar(4000);
update users set password = 'password';