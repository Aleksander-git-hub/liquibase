CREATE TABLE cart_item(
    cart_id BIGINT REFERENCES carts(id) ON DELETE RESTRICT,
    item_id BIGINT REFERENCES items(id) ON DELETE RESTRICT
);

OK