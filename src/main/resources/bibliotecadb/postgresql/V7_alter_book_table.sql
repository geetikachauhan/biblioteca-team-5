ALTER TABLE biblioteca.book ADD COLUMN isbn VARCHAR;
ALTER TABLE biblioteca.book ADD CONSTRAINT isbnUnique UNIQUE (isbn);
UPDATE biblioteca.book set isbn = '978-1-60309-025-4' where id=1;
UPDATE biblioteca.book set isbn = '978-1-60309-047-6' where id=2;
UPDATE biblioteca.book set isbn = '978-1-60309-322-4' where id=3;
UPDATE biblioteca.book set isbn = '978-1-891830-85-3' where id=4;
UPDATE biblioteca.book set isbn = '978-1-60309-016-2' where id=5;
UPDATE biblioteca.book set isbn = '978-1-60309-265-4' where id=6;