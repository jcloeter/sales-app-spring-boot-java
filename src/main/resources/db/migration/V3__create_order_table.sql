CREATE TABLE IF NOT EXISTS "order" (
    id          BIGINT PRIMARY KEY,
    user_id     BIGINT REFERENCES "user"(id),
    created_ON  TIMESTAMP WITH TIME ZONE
);

CREATE INDEX IF NOT EXISTS order_user_id_idx
    ON "order"(user_id);