drop table if exists user;

create table user
(
  user_id        bigint       not null auto_increment,
  first_name     varchar(255) not null,
  last_name      varchar(255) not null,
  email          varchar(255) not null,
  password       varchar(255) not null,
  phone          varchar(255) not null,
  creation_date  datetime     not null,
  is_active      boolean      not null,
  role_id     bigint  not null,
  primary key (user_id),
  key role_id (role_id),
  constraint fk_role
  foreign key (role_id) references role (role_id)

) engine = InnoDB;
