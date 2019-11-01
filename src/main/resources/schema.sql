create table users_table(
    email varchar_ignorecase(50) not null primary key,
    password varchar_ignorecase(50) not null,
    enabled boolean not null
);

create table users_authorities (
    email varchar_ignorecase(50) not null,
    authority varchar_ignorecase(50) not null,
    constraint fk_authorities_users foreign key(email) references users_table(email)
);
create unique index ix_auth_username on users_authorities (email,authority);