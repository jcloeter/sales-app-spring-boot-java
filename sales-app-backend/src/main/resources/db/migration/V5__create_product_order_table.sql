CREATE TABLE IF NOT EXISTS product_order(
    id          BIGINT PRIMARY KEY,
    product_id  BIGINT REFERENCES product(id),
    order_id    BIGINT REFERENCES orders(id)
);

CREATE INDEX IF NOT EXISTS product_order_product_id_idx 
    ON product_order(product_id);

CREATE INDEX IF NOT EXISTS product_orer_order_id_idx
    ON product_order(order_id);    