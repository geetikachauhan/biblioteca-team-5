CREATE TABLE IF NOT EXISTS biblioteca.book (
    id SERIAL,
    title VARCHAR NOT NULL,
    available BOOLEAN,
    author VARCHAR,
    year_published INTEGER,
    isbn VARCHAR ,
    PRIMARY KEY (id)
);

ALTER TABLE biblioteca.book ADD CONSTRAINT isbnUnique UNIQUE (isbn);
