create table if not exists content (
    id integer auto_increment,
    title varchar(255) not null,
    description text,
    status varchar(32) not null,
    content_type varchar(50) not null,
    created_at timestamp not null,
    updated_at timestamp,
    url varchar(255),

    primary key(id)
);

insert into content (title, description, status, content_type, created_at)
    values ('My spring data blog post', 'A post about spring data', 'IDEA', 'ARTICLE', CURRENT_TIMESTAMP);
