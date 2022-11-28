drop table if exists summary;

create table summary(

summary_id     bigint not null auto_increment primary key,
amount INTEGER not null,
product_id bigint not null,
constraint fk_product foreign key (product_id) references product(product_id)
)