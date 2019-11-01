insert into users_table (email , password , enabled ) values ('user@sec.com', 'pass', true ) ;
insert into users_table (email , password , enabled ) values ('admin@sec.com', 'pass', true ) ;
insert into users_authorities (email , authority) values ('user@sec.com' , 'ROLE_USER');
insert into users_authorities (email , authority) values ('admin@sec.com' , 'ROLE_ADMIN');
