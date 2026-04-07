-- ========================
-- ROLES
-- ========================
INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');

-- ========================
-- USERS
-- password: 123456 (plain, chưa encode)
-- ========================
INSERT INTO users (id, email, password, username, role_id)
VALUES (1, 'user@gmail.com', '123456', 'Normal User', 1);

INSERT INTO users (id, email, password, username, role_id)
VALUES (2, 'admin@gmail.com', '123456', 'Admin User', 2);

-- ========================
-- CATEGORIES
-- ========================
INSERT INTO categories (id, name) VALUES (1, 'Electronics');
INSERT INTO categories (id, name) VALUES (2, 'Fashion');

-- ========================
-- PRODUCTS
-- ========================
INSERT INTO products (id, name, description, price, stock, category_id)
VALUES (1, 'iPhone 15', 'Latest Apple smartphone with A16 chip', 1000, 50, 1);

INSERT INTO products (id, name, description, price, stock, category_id)
VALUES (2, 'AirPods Pro', 'Wireless earbuds with noise cancellation', 250, 100, 1);

INSERT INTO products (id, name, description, price, stock, category_id)
VALUES (3, 'T-Shirt', 'Comfortable cotton t-shirt', 20, 200, 2);

INSERT INTO products (id, name, description, price, stock, category_id)
VALUES (4, 'Jeans', 'Slim fit denim jeans', 50, 150, 2);

-- ========================
-- CART ITEMS (optional)
-- ========================
INSERT INTO cart_items (id, user_id, product_id, quantity)
VALUES (1, 1, 1, 1);

INSERT INTO cart_items (id, user_id, product_id, quantity)
VALUES (2, 1, 2, 2);