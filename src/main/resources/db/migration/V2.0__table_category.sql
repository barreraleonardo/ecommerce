drop table if exists category;

create table category(

category_id     bigint not null auto_increment primary key,
name varchar(30) unique
)