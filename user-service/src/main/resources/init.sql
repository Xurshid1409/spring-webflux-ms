create table users(
    id bigserial,
    user_name varchar(255) not null,
    balance decimal,
    created_date timestamp,
    updated_date timestamp,
    primary key (id)
);
create table user_transaction(
    id bigserial,
    user_id bigint,
    amount decimal,
    created_date timestamp,
    updated_date timestamp,
    primary key (id),
    foreign key (user_id) references users(id)
);