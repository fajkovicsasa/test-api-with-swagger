DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id BIGINT NOT NULL,
    email VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
);
