ALTER TABLE biblioteca.book ADD COLUMN available BOOLEAN;
UPDATE biblioteca.book set available = true;
