create table authors (
    id integer not null auto_increment,
    name varchar(255),
    primary key (id)
);

create table books (
    id integer not null auto_increment,
    description varchar(255),
    price float,
    title varchar(255),
    primary key (id)
);

create table books_authors (
    author_id integer not null,
    book_id integer not null
);

create table books_genres (
    genre_id integer not null,
    book_id integer not null
);

create table genres (
    id integer not null auto_increment,
    name varchar(255),
    primary key (id)
);

create table orders (
    id integer not null auto_increment,
    address varchar(255),
    quantity integer,
    first_name varchar(255),
    last_name varchar(255),
    book_id integer,
    primary key (id)
);

alter table books_authors add constraint books_authors_fk0 foreign key (book_id) references authors (id);

alter table books_authors add constraint books_authors_fk1 foreign key (author_id) references books (id);

alter table books_genres add constraint books_genres_fk0 foreign key (book_id) references genres (id);

alter table books_genres add constraint books_genres_fk1 foreign key (genre_id) references books (id);

alter table orders add constraint order_books_fk0 foreign key (book_id) references books (id);