ALTER TABLE users ADD COLUMN created_at TIMESTAMP;
ALTER TABLE users ALTER COLUMN created_at SET DEFAULT now();
ALTER TABLE users DROP COLUMN created_on; 

ALTER TABLE orders ADD COLUMN created_at TIMESTAMP;
ALTER TABLE orders ALTER COLUMN created_at SET DEFAULT now();
ALTER TABLE orders DROP COLUMN created_on;

ALTER TABLE product ADD COLUMN created_at TIMESTAMP;
ALTER TABLE product ALTER COLUMN created_at SET DEFAULT now();