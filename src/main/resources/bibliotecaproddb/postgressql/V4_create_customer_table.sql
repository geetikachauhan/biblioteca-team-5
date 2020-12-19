CREATE TABLE IF NOT EXISTS biblioteca.customer (
    id SERIAL,
    name VARCHAR NOT NULL,
    library_number VARCHAR NOT NULL,
    password TEXT NOT NULL,
    email VARCHAR,
    phone VARCHAR,
    PRIMARY KEY (id)
);

ALTER TABLE biblioteca.customer ADD CONSTRAINT libNumUniq UNIQUE (library_number);
