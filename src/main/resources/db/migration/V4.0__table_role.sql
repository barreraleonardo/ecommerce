drop table if exists role;
create table role (
  role_id bigint auto_increment,
  name varchar(255) not null,
  description varchar(255) not null,
  creation_date datetime not null,
  primary key (role_id)
);
