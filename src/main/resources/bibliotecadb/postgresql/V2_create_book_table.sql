CREATE TABLE IF NOT EXISTS ${schema}book (
    id SERIAL,
    title VARCHAR NOT NULL,
    PRIMARY KEY (id)
);