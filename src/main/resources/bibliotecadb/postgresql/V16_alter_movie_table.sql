ALTER TABLE biblioteca.movie ADD COLUMN available BOOLEAN;
UPDATE biblioteca.movie set available = true;
