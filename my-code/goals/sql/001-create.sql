drop table if exists goals;

create table goals (
       id text,
       description text,
       is_done boolean,
       primary key (id)
);
