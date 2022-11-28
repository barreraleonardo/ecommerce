drop table if exists product;

create table product(

 product_id       bigint not null auto_increment primary key ,
 name varchar(30)  unique,
 price double not null,
 detail TEXT not null,
 image varchar(255) not null,
 is_available boolean null,
 stock bigint not null,
 category_id bigint  null,
 mark_id bigint  null,
 constraint fk_category foreign key (category_id) references category(category_id),
 constraint fk_mark foreign key (mark_id) references mark(mark_id)
)