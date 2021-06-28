create table Author(
id bigint IDENTITY(1,1) PRIMARY KEY,
name nvarchar(200) not null,
CONSTRAINT UC_Author UNIQUE (name)
)

create table Publisher(
id bigint IDENTITY(1,1) PRIMARY KEY,
name nvarchar(200) not null,
address nvarchar(500),
CONSTRAINT UC_Publisher UNIQUE (name, address))

/*
Alter queries for adding/deleting constraints
alter table Publisher
add CONSTRAINT UC_Publisher UNIQUE (name, address)

alter table Publisher
drop constraint UC_Publisher

When Auto increment value has been increased, 
emptying the table will not reset the value to 1
To reset the value use below
DBCC CHECKIDENT (Publisher, RESEED, 0)
*/


create table Book(
id bigint IDENTITY(1,1) PRIMARY KEY,
title varchar(500) not null,
publication_year int,
book_genre_id tinyint,
amazon_rating decimal(18,0),
kid_friendly_status tinyint,
created_date datetime not null DEFAULT GETDATE(),
publisher_id bigint FOREIGN KEY REFERENCES Publisher(id)
CONSTRAINT UC_Book UNIQUE (title, publication_year, publisher_id),
)

alter table Book
alter column amazon_rating decimal(18, 0)

create table Book_Author(
id bigint IDENTITY(1,1) PRIMARY KEY,
book_id bigint not null FOREIGN KEY REFERENCES Book(id),
author_id bigint not null FOREIGN KEY REFERENCES Author(id)
CONSTRAINT UC_Book_Author UNIQUE (book_id, author_id)
)

/*#Cannot perform alter if table has unique key reference on column
Alter table Book_Author
alter column book_id bigint not null;
*/

create table Actor(
id bigint IDENTITY(1,1) PRIMARY KEY,
name nvarchar(200) not null,
CONSTRAINT UC_Actor UNIQUE (name)
)


create table Director(
id bigint IDENTITY(1,1) PRIMARY KEY,
name nvarchar(200) not null,
CONSTRAINT UC_Director UNIQUE (name)
)

create table Movie(
id bigint IDENTITY(1,1) PRIMARY KEY,
title nvarchar(500) not null,
release_year int,
movie_genre_id tinyint,
imdb_rating decimal(18, 0),
kid_friendly_status tinyint,
created_date datetime not null DEFAULT GETDATE(),
CONSTRAINT UC_Movie UNIQUE (title, release_year),
)


create table Movie_Actor(
id bigint IDENTITY(1,1) PRIMARY KEY,
movie_id bigint not null FOREIGN KEY REFERENCES Movie(id),
actor_id bigint not null FOREIGN KEY REFERENCES Actor(id),
CONSTRAINT UC_Movie_Actor UNIQUE (movie_id, actor_id)
)

create table Movie_Director(
id bigint IDENTITY(1,1) PRIMARY KEY,
movie_id bigint not null FOREIGN KEY REFERENCES Movie(id),
director_id bigint not null FOREIGN KEY REFERENCES Actor(id),
CONSTRAINT UC_Movie_Director UNIQUE (movie_id, director_id)
)

create table WebLink(
id bigint not null IDENTITY(1,1) PRIMARY KEY,
title nvarchar(500) not null,
url nvarchar(500) not null,
host nvarchar(250) not null,
kid_friendly_status tinyint,
created_date datetime not null DEFAULT GetDate(),
CONSTRAINT UC_WebLink UNIQUE (url)
)

-- Use quotes[] in SQL Server for reserved keywords such as User
-- Else change the table name(changed to Users)

create table Users(
id bigint not null IDENTITY(1,1) PRIMARY KEY,
email nvarchar(100) not null,
password nvarchar(50) not null,
first_name nvarchar(100) not null,
last_name nvarchar(100) not null,
gender_id tinyint,
user_type_id tinyint,
created_date datetime not null DEFAULT GetDate(),
CONSTRAINT UC_Users UNIQUE (email)
)


create table Users_Book(
id bigint not null IDENTITY(1,1) PRIMARY KEY,
users_id bigint not null FOREIGN KEY REFERENCES Users(id),
book_id bigint not null FOREIGN KEY REFERENCES Book(id)
CONSTRAINT UC_Users_Book UNIQUE (users_id, book_id)
)

create table Users_Movie(
id bigint not null IDENTITY(1,1) PRIMARY KEY,
users_id bigint not null FOREIGN KEY REFERENCES Users(id),
movie_id bigint not null FOREIGN KEY REFERENCES Movie(id)
CONSTRAINT UC_Users_Movie UNIQUE (users_id, movie_id)
)

create table Users_WebLink(
id bigint not null IDENTITY(1,1) PRIMARY KEY,
users_id bigint not null FOREIGN KEY REFERENCES Users(id),
weblink_id bigint not null FOREIGN KEY REFERENCES WebLink(id)
CONSTRAINT UC_Users_WebLink UNIQUE (users_id, weblink_id)
)

-- Alter table queries
ALTER TABLE Book 
add 
kid_friendly_marked_by bigint FOREIGN KEY REFERENCES Users(id),
shared_by bigint FOREIGN KEY REFERENCES Users(id);

ALTER TABLE WebLink 
ADD 
kid_friendly_marked_by bigint FOREIGN KEY REFERENCES Users(id),
shared_by bigint FOREIGN KEY REFERENCES Users(id)

ALTER TABLE Movie 
ADD
kid_friendly_marked_by bigint FOREIGN KEY REFERENCES Users(id);



-- Insert Queries
-- Authors
INSERT INTO Author (name) VALUES ('Henry David Thoreau');
INSERT INTO Author (name) VALUES ('Ralph Waldo Emerson');
INSERT INTO Author (name) VALUES ('Lillian Eichler Watson');
-- Multiple rows in same query
INSERT INTO Author (name) VALUES ('Eric Freeman'),('Bert Bates'), ('Kathy Sierra'), ('Elisabeth Robson');
INSERT INTO Author (name) VALUES ('Joshua Bloch');

-- Publishers
INSERT INTO Publisher (name) VALUES ('Wilder Publications');
INSERT INTO Publisher (name) VALUES ('Dover Publications');
INSERT INTO Publisher (name) VALUES ('Touchstone');
INSERT INTO Publisher (name) VALUES ('O''Reilly Media');
INSERT INTO Publisher (name) VALUES ('Prentice Hall');


-- Books
INSERT INTO Book (title, publication_year, publisher_id, book_genre_id, amazon_rating, kid_friendly_status) VALUES ('Walden',1854, 1, 6, 4.3, 2);
INSERT INTO Book (title, publication_year, publisher_id, book_genre_id, amazon_rating, kid_friendly_status) VALUES
('Self-Reliance and Other Essays', 1993, 2, 6, 4.5, 2);
INSERT INTO Book (title, publication_year, publisher_id, book_genre_id, amazon_rating, kid_friendly_status) VALUES 
('Light From Many Lamps', 1988, 3, 6, 5.0, 2);
INSERT INTO Book (title, publication_year, publisher_id, book_genre_id, amazon_rating, kid_friendly_status) VALUES 
('Head First Design Patterns', 2004, 4, 10, 4.5, 2);
INSERT INTO Book (title, publication_year, publisher_id, book_genre_id, amazon_rating, kid_friendly_status) VALUES
('Effective Java Programming Language Guide', 2007, 5, 10, 4.9, 2);


-- Book_Author
INSERT INTO Book_Author (book_id, author_id) VALUES 
(1, 1), (2, 2), (3, 3), (4, 4), (4, 5), (4, 6), (4,7);

-- Actor
INSERT INTO actor (name) VALUES ('Orson Welles'),('Joseph Cotten'), ('Henry Fonda'), 
('Jane Darwell'), ('Albert Cullum'), ('Kaley Cuoco'), ('Jim Parsons'), ('Takashi Shimura'), ('Minoru Chiaki');

-- Director
INSERT INTO director (name) VALUES ('Orson Welles'), ('John Ford'), ('Leslie Sullivan'), 
('Chuck Lorre'), ('Bill Prady'), ('Akira Kurosawa');

-- Movie
INSERT INTO movie (title, release_year, movie_genre_id, imdb_rating, kid_friendly_status) VALUES ('Citizen Kane', 1941, 0, 8.5, 2);
INSERT INTO movie (title, release_year, movie_genre_id, imdb_rating, kid_friendly_status) VALUES ('The Grapes of Wrath', 1940, 0, 8.2, 2);
INSERT INTO movie (title, release_year, movie_genre_id, imdb_rating, kid_friendly_status) VALUES ('A Touch of Greatness', 2004, 24, 7.3, 2);
INSERT INTO movie (title, release_year, movie_genre_id, imdb_rating, kid_friendly_status) VALUES ('The Big Bang Theory', 2007, 20, 8.7, 2);
INSERT INTO movie (title, release_year, movie_genre_id, imdb_rating, kid_friendly_status) VALUES ('Ikiru', 1952, 25, 8.4, 2);

-- Movie_Actor
INSERT INTO movie_actor (movie_id, actor_id) VALUES (1, 1), (1, 2), (2, 3), (2, 4), (3, 5), (4, 6), (4, 7), (5, 8), (5, 9);

-- Movie_Director
INSERT INTO movie_director(movie_id, director_id) VALUES (1, 1), (2, 2), (3, 3), (4, 4), (4, 5), (5, 6);


-- WebLink
INSERT INTO WebLink (title, url, host, kid_friendly_status) VALUES ('Use Final Liberally', 'http://www.javapractices.com/topic/TopicAction.do?Id=23', 'http://www.javapractices.com', 2);
INSERT INTO WebLink (title, url, host, kid_friendly_status) VALUES ('How do I import a pre-existing Java project into Eclipse and get up and running?', 'https://stackoverflow.com/questions/142863/how-do-i-import-a-pre-existing-java-project-into-eclipse-and-get-up-and-running', 'http://stackoverflow.com', 2);
INSERT INTO WebLink (title, url, host, kid_friendly_status) VALUES ('Interface vs Abstract Class',
'http://mindprod.com/jgloss/interfacevsabstract.html', 'http://mindprod.com', 2);
INSERT INTO WebLink (title, url, host, kid_friendly_status) VALUES ('NIO tutorial by Greg Travis',
'http://cs.brown.edu/courses/cs161/papers/j-nio-ltr.pdf', 'http://cs.brown.edu', 2);
INSERT INTO WebLink (title, url, host, kid_friendly_status) VALUES ('VirtualHosting and Tomcat',
'http://tomcat.apache.org/tomcat-6.0-doc/virtual-hosting-howto.html', 'http://tomcat.apache.org', 2);

-- Users
INSERT INTO Users (email, password, first_name, last_name, gender_id, user_type_id)
VALUES ('ankit@crypto.com', 'test', 'ankit', 'T', 0, 0);
INSERT INTO Users (email, password, first_name, last_name, gender_id, user_type_id)
VALUES ('abhishek@crypto.com', 'test', 'abhishek', 'T', 0, 0);
INSERT INTO Users (email, password, first_name, last_name, gender_id, user_type_id)
VALUES ('nikita@crypto.com', 'test', 'nikita', 'T', 1, 1);
INSERT INTO Users (email, password, first_name, last_name, gender_id, user_type_id)
VALUES ('shubhi@crypto.com', 'test', 'shubhi', 'T', 1, 1);
INSERT INTO Users (email, password, first_name, last_name, gender_id, user_type_id)
VALUES ('ajay@crypto.com', 'test', 'ajay', 'T', 0, 2);


