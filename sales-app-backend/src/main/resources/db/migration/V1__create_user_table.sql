-- user is a reserved postgres keyword so it must be wrapped in quotation marks 
CREATE TABLE IF NOT EXISTS "user" (
    id         BIGINT PRIMARY KEY,
    first_name TEXT,
    last_name  TEXT,
    created_on TIMESTAMP WITH TIME ZONE
);

CREATE INDEX IF NOT EXISTS user_last_name_idx
    ON "user"(last_name);