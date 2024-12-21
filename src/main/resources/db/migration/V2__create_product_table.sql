CREATE TABLE IF NOT EXISTS product (
    id BIGINT PRIMARY KEY,
    name TEXT,
    description TEXT,
    price DECIMAL
);

CREATE INDEX IF NOT EXISTS product_name_idx 
     ON product(name);