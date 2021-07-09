-- Select queries
select * from Book
select * from Author
select * from Book_Author
select * from publisher

select title, name from Book, Publisher

select title, name from Book inner join Publisher on Book.publisher_id=Publisher.id

-- Populate loadBooks method
select b.id, b.title, b.publication_year,p.name, 
STUFF((SELECT ', ' + a.name
           FROM Author a
		   join Book_Author ba
		   on a.id = ba.author_id
		   and ba.book_id=b.id
          FOR XML PATH('')), 1, 2, '') as authors,
b.book_genre_id, b.amazon_rating, created_date from Book b, Author a, Publisher p, 
Book_Author ba where b.publisher_id=p.id and b.id = ba.book_id and ba.author_id = a.id
group by b.id,b.title, b.publication_year, p.name, b.book_genre_id, b.amazon_rating, b.created_date
;

select * from Actor
select * from Movie

-- populate loadMovies method
Select m.id, m.title, m.release_year,
STUFF((SELECT ', ' + a.name
           FROM Actor a
		   join Movie_Actor ma
		   on a.id = ma.actor_id
		   and ma.movie_id=m.id
          FOR XML PATH('')), 1, 2, '') as actors,
STUFF((SELECT ', ' + d.name
           FROM Director d
		   join Movie_Director md
		   on d.id = md.director_id
		   and md.movie_id=m.id
          FOR XML PATH('')), 1, 2, '') as directors,
m.movie_genre_id, m.imdb_rating , m.created_date
from Movie m, Actor a, Movie_Actor ma, Director d, Movie_Director md 
where m.id = ma.movie_id and ma.actor_id = a.id 
and m.id = md.movie_id and md.director_id = d.id 
group by m.id,m.title, m.release_year,m.movie_genre_id, m.imdb_rating, m.created_date;


-- load weblinks
select id, title, url, host, created_date
from WebLink

-- load Users
SELECT id, email, password, first_name, last_name, gender_id, user_type_id, created_date
FROM Users


-- check table population
select * from Users_Book;
select * from Users_Movie;
select * from Users_WebLink;

-- 
SELECT kid_friendly_marked_by, shared_by, *
FROM Book

SELECT kid_friendly_marked_by, shared_by, *
FROM WebLink

SELECT kid_friendly_marked_by, *
FROM Movie

-- Populate getBooks method
select b.id, b.title, b.image_url, b.publication_year,p.name, 
STUFF((SELECT ', ' + a.name
           FROM Author a
		   join Book_Author ba
		   on a.id = ba.author_id
		   and ba.book_id=b.id
          FOR XML PATH('')), 1, 2, '') as authors,
b.book_genre_id, b.amazon_rating, created_date from Book b, Author a, Publisher p, 
Book_Author ba where b.publisher_id=p.id and b.id = ba.book_id and ba.author_id = a.id
and b.id not in (select ub.book_id from Users u, Users_Book ub where u.id = 5 and u.id = ub.users_id)
group by b.id, b.title, b.image_url, b.publication_year, p.name, b.book_genre_id, b.amazon_rating, b.created_date
;