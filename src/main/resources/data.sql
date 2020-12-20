insert into book(title, author, year_published, isbn, available)
values ('A Game of Thrones', 'George R. R. Martin', 1996, '978-1-60309-025-4', true);
insert into book(title, author, year_published, isbn, available)
values ('The Fellowship of the Ring', 'J. R. R. Tolkien', 1954, '978-1-60309-047-6', true);
insert into book(title, author, year_published, isbn, available)
values ('The Lion, the Witch and the Wardrobe', 'C. S. Lewis', 1950, '978-1-60309-322-4', true);
insert into book(title, author, year_published, isbn, available)
values ('The Colour of Magic', 'Terry Pratchett', 1983, '978-1-891830-85-3', false);
insert into book(title, author, year_published, isbn, available)
values ('Assassin’s Apprentice', 'Robin Hobb', 1995, '978-1-60309-016-2', true);
insert into book(title, author, year_published, isbn, available)
values ('The Lies of Locke Lamora', 'Scott Lynch', 2006, '978-1-60309-265-4', true);
insert into book(title, author, year_published, isbn, available)
values ('A Game of Thrones', 'George R. R. Martin', 1996, '978-1-60309-123-4', true);
insert into book(title, author, year_published, isbn, available)
values ('The Fellowship of the Ring', 'J. R. R. Tolkien', 1954, '978-1-60309-321-6', true);

insert into movie(movie_name, movie_year, director, rating, available)
values ('Harry Potter and the Philosopher’s Stone', 2001, 'Chris Columbus', 7.6, true);
insert into movie(movie_name, movie_year, director, rating, available)
values ('Narnia: The Lion, the Witch and the Wardrobe', 2005, 'Andrew Adamson', 7.2, true);
insert into movie(movie_name, movie_year, director, rating, available)
values ('The Fellowship of the Ring', 2001, 'Peter Jackson', 8.9, true);
insert into movie(movie_name, movie_year, director, rating, available)
values ('How to Train Your Dragon', 2010, 'Chris Sanders', 8.2, true);

insert into customer(name, library_number, password, email, phone)
values ('test', 'test', 'test', 'test@test.com', '5678902356');
insert into customer(name, library_number, password, email, phone)
values ('Guest', 'Guest', 'guest', 'guest@guest.com', '123456789')
