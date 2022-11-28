drop table if  exists invoice_summary;

create table invoice_summary(

   invoice_id bigint not null,
   summary_id bigint unique not null,
   constraint fk_invoice foreign key (invoice_id) references invoice (invoice_id),
   constraint fk_summary foreign key (summary_id) references summary (summary_id)

)engine = InnoDB;