drop table if exists runs;

create table runs (
    run_id int not null,
    title varchar(250) not null,
    miles int not null,
    primary key(run_id)
);

insert into runs
        (run_id, title, miles)
    values
        (1, 'Monday morning run', 3),
        (2, 'Wednesday afternoon run', 5),
        (3, 'Saturday morning run', 8);
