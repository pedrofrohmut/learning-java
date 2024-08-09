drop table if exists users;

drop table if exists todos;

drop table if exists todo_items;

create extension if not exists "uuid-ossp";

create table if not exists users (
    id UUID default uuid_generate_v4(),
    name text not null,
    email text not null,
    password_hash text not null,
    phone text not null,
    created_at timestamp default now(),
    updated_at timestamp,
    primary key(id)
);

create table if not exists todos (
    id UUID default uuid_generate_v4(),
    name text not null,
    description text,
    user_id UUID not null,
    created_at timestamp default now(),
    updated_at timestamp,
    primary key(id),
    constraint fk_todo_user foreign key (user_id) references users(id) on delete cascade
);

create table if not exists todo_items (
    id UUID default uuid_generate_v4(),
    name text not null,
    description text,
    user_id UUID not null,
    todo_id UUID not null,
    created_at timestamp default now(),
    updated_at timestamp,
    primary key(id),
    constraint fk_todo_item_user foreign key (user_id) references users(id),
    constraint fk_todo_item_todo foreign key (todo_id) references todos(id) on delete cascade
);
