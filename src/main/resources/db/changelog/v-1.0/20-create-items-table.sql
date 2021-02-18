CREATE TABLE items(
    id serial PRIMARY KEY ,
    name VARCHAR (255),
    description TEXT,
    price NUMERIC,
    department_id INTEGER REFERENCES departments(id) ON DELETE RESTRICT
);

OK