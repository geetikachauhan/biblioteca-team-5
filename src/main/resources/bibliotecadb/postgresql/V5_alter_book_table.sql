ALTER TABLE biblioteca.book ADD COLUMN author VARCHAR;
UPDATE biblioteca.book set author = 'George R. R. Martin' where title = 'A Game of Thrones';
UPDATE biblioteca.book set author = 'J. R. R. Tolkien' where title = 'The Fellowship of the Ring';
UPDATE biblioteca.book set author = 'C. S. Lewis' where title = 'The Lion, the Witch and the Wardrobe';
UPDATE biblioteca.book set author = 'Terry Pratchett' where title = 'The Colour of Magic';
UPDATE biblioteca.book set author = 'Robin Hobb' where title = 'Assassin’s Apprentice';
UPDATE biblioteca.book set author = 'Scott Lynch' where title = 'The Lies of Locke Lamora';