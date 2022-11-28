drop table if  exists invoice;

create table invoice(

   invoice_id bigint auto_increment primary key,
   user_id bigint not null,
   date TIMESTAMP not null,
   total_price FLOAT not null,
   constraint fk_user foreign key (user_id) references user (user_id)

)engine = InnoDB;