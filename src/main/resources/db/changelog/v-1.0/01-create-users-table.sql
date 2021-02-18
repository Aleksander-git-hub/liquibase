CREATE TABLE users(
    id bigserial PRIMARY KEY,
    first_name VARCHAR (255),
    second_name VARCHAR (255),
    phone VARCHAR (30),
    email VARCHAR (255),
    deleted BOOLEAN
);

OK