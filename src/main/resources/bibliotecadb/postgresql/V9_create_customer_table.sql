CREATE TABLE IF NOT EXISTS biblioteca.customer (
    id SERIAL,
    name VARCHAR NOT NULL,
    library_number VARCHAR NOT NULL,
    password TEXT NOT NULL,
    PRIMARY KEY (id)
);