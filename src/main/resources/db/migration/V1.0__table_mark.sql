drop table if exists mark;

create table mark (
mark_id     bigint not null auto_increment primary key,
name varchar(30) unique,
is_available boolean not null

)