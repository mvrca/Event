CREATE TABLE event(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    place VARCHAR(255),
    date DATE,
    time TIME,
    descricao TEXT
);
