ALTER TABLE biblioteca.book ADD COLUMN year_published INTEGER ;
UPDATE biblioteca.book set year_published = 1996 where title = 'A Game of Thrones';
UPDATE biblioteca.book set year_published = 1954 where title = 'The Fellowship of the Ring';
UPDATE biblioteca.book set year_published = 1950 where title = 'The Lion, the Witch and the Wardrobe';
UPDATE biblioteca.book set year_published = 1983 where title = 'The Colour of Magic';
UPDATE biblioteca.book set year_published = 1995 where title = 'Assassin’s Apprentice';
UPDATE biblioteca.book set year_published = 2006 where title = 'The Lies of Locke Lamora';