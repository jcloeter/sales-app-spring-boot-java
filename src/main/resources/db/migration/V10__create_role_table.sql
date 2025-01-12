
CREATE TABLE IF NOT EXISTS role (
    id                  BIGSERIAL PRIMARY KEY,
    name                TEXT,
    display_name        TEXT,  
    description         TEXT
);

CREATE TABLE IF NOT EXISTS role_hierarchy (
    id                  BIGSERIAL PRIMARY KEY,
    parent_role_id      BIGINT REFERENCES role(id) ON DELETE CASCADE,
    child_role_id       BIGINT REFERENCES role(id) ON DELETE CASCADE
);

INSERT INTO role (id, name, display_name, description) VALUES 
    (1, 'USER', 'User', 'Shopper who can purchase goods'),
    (2, 'MERCHANT', 'Merchant', 'Sells and manages their products'),
    (3, 'ADMIN', 'Admin', 'Manages order and products'),
    (4, 'SUPER_ADMIN', 'Super Admin', 'Manages users, order, and products')
ON CONFLICT DO NOTHING; 

INSERT INTO role_hierarchy (parent_role_id, child_role_id) 
    VALUES (2, 1),
           (3, 1), (3, 2),
           (4, 1), (4,2), (4, 3)
    ON CONFLICT DO NOTHING;

ALTER TABLE users 
ADD COLUMN IF NOT EXISTS role_id BIGINT REFERENCES role(id);

CREATE INDEX IF NOT EXISTS users_role_id_idx 
    ON users (role_id);