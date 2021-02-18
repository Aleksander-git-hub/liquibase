CREATE TABLE items(
    id bigserial PRIMARY KEY ,
    name VARCHAR (255),
    description TEXT,
    price DOUBLE PRECISION,
    deleted BOOLEAN,
    department_id BIGINT REFERENCES departments(id) ON DELETE RESTRICT
);

OK