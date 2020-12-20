CREATE TABLE IF NOT EXISTS biblioteca.movie (
    id SERIAL,
    movie_name VARCHAR NOT NULL,
    movie_year INTEGER,
    director VARCHAR,
    rating DECIMAL ,
    available BOOLEAN,
    PRIMARY KEY (id)
);