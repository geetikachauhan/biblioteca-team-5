CREATE TABLE IF NOT EXISTS ${schema_name}.book (
    id SERIAL,
    title VARCHAR NOT NULL,
    PRIMARY KEY (id)
);