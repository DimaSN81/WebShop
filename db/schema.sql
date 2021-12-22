CREATE TABLE Product(
                        id  SERIAL PRIMARY KEY,
                        name CHAR(100) NOT NULL,
                        price double precision,
                        creationDate  TIMESTAMP
);

CREATE TABLE Users (
                       id SERIAL PRIMARY KEY,
                       email VARCHAR(100) NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       sole VARCHAR(100)  NOT NULL,
                       firstName VARCHAR(100) NOT NULL,
                       lastName VARCHAR(100) NOT NULL
);