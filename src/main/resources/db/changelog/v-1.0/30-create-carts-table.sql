CREATE TABLE carts(
    id bigserial PRIMARY KEY,
    broken BOOLEAN,
    user_id BIGINT REFERENCES users(id) ON DELETE RESTRICT
);

OK