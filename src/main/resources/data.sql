insert into book(title, author, year_published, isbn, available)
values ('A Game of Thrones', 'George R. R. Martin', 1996, '978-1-60309-625-4', true);
insert into book(title, author, year_published, isbn, available)
values ('The Fellowship of the Ring', 'J. R. R. Tolkien', 1954, '978-1-634309-047-6', true);
insert into book(title, author, year_published, isbn, available)
values ('The Lion, the Witch and the Wardrobe', 'C. S. Lewis', 1950, '978-1-603549-322-4', true);
insert into book(title, author, year_published, isbn, available)
values ('The Colour of Magic', 'Terry Pratchett', 1983, '978-1-8918320-85-3', false);
insert into book(title, author, year_published, isbn, available)
values ('Assassin’s Apprentice', 'Robin Hobb', 1995, '978-1-603129-016-2', true);
insert into book(title, author, year_published, isbn, available)
values ('The Lies of Locke Lamora', 'Scott Lynch', 2006, '978-1-603509-265-4', true);
insert into book(title, author, year_published, isbn, available)
values ('A Game of Thrones', 'George R. R. Martin', 1996, '978-1-603349-234-4', false);
insert into book(title, author, year_published, isbn, available)
values ('The Fellowship of the Ring', 'J. R. R. Tolkien', 1954, '978-1-603109-321-6', false);

insert into movie(movie_name, movie_year, director, rating, available)
values ('Harry Potter and the Philosopher’s Stone', 2001, 'Chris Columbus', 7.6, true);
insert into movie(movie_name, movie_year, director, rating, available)
values ('Narnia: The Lion, the Witch and the Wardrobe', 2005, 'Andrew Adamson', 7.2, true);
insert into movie(movie_name, movie_year, director, rating, available)
values ('The Fellowship of the Ring', 2001, 'Peter Jackson', 8.9, true);
insert into movie(movie_name, movie_year, director, rating, available)
values ('How to Train Your Dragon', 2010, 'Chris Sanders', 8.2, true);

insert into customer(name, library_number, password, email, phone)
values ('test', 'test', '$2a$10$M/y/L6IrYaujg6iiKIH6S.iggzpXmYHRNDwRPx5E2ygCWA4fZY4OS', 'test@test.com', '5678902356');
insert into customer(name, library_number, password, email, phone)
values ('Guest', 'Guest', '$2a$10$XBCWw1OqScGVVMBbKi1P1eDmpaMsnGGeEKTbhwRkhzyFS3vSUmyUi', 'guest@guest.com', '123456789')
