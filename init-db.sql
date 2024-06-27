-- Crear la base de datos si no existe
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'hawadb')
BEGIN
    CREATE DATABASE hawadb;
END
GO

USE hawadb;
GO

-- Crear tablas si no existen
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'app_user')
BEGIN
CREATE TABLE app_user (
                          id INT IDENTITY PRIMARY KEY,
                          created_at DATETIME2(6),
                          email VARCHAR(255),
                          firstname VARCHAR(255),
                          fullname VARCHAR(255),
                          lastname VARCHAR(255),
                          password VARCHAR(255),
                          role VARCHAR(255) CHECK (role = 'USER' OR role = 'ADMIN'),
                          updated_at DATETIME2(6)
);
END
GO

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'customer')
BEGIN
CREATE TABLE customer (
                          id BIGINT IDENTITY PRIMARY KEY,
                          email VARCHAR(255),
                          name VARCHAR(255),
                          phone VARCHAR(255)
);
END
GO

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'truck')
BEGIN
CREATE TABLE truck (
                       id BIGINT IDENTITY PRIMARY KEY,
                       brand VARCHAR(255) NOT NULL,
                       discount NUMERIC(38, 2),
                       model VARCHAR(255),
                       name VARCHAR(255),
                       price NUMERIC(38, 2),
                       stock INT NOT NULL
);
END
GO

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'store')
BEGIN
CREATE TABLE store (
                       id BIGINT IDENTITY PRIMARY KEY,
                       location VARCHAR(255),
                       name VARCHAR(255)
);
END
GO

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'seller')
BEGIN
CREATE TABLE seller (
                        id BIGINT IDENTITY PRIMARY KEY,
                        name VARCHAR(255),
                        store_id BIGINT,
                        FOREIGN KEY (store_id) REFERENCES store(id)
);
END
GO

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'orders')
BEGIN
CREATE TABLE orders (
                        id BIGINT IDENTITY PRIMARY KEY,
                        order_date DATETIME2(6),
                        status VARCHAR(255) CHECK (status = 'CANCELLED' OR status = 'DELIVERED' OR status = 'PENDING'),
                        total_price NUMERIC(38, 2),
                        customer_id BIGINT,
                        seller_id BIGINT,
                        store_id BIGINT,
                        FOREIGN KEY (customer_id) REFERENCES customer(id),
                        FOREIGN KEY (seller_id) REFERENCES seller(id),
                        FOREIGN KEY (store_id) REFERENCES store(id)
);
END
GO

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'orders_trucks')

BEGIN
CREATE TABLE orders_trucks (
                               orders_id BIGINT NOT NULL,
                               trucks_id BIGINT NOT NULL,
                               FOREIGN KEY (orders_id) REFERENCES orders(id),
                               FOREIGN KEY (trucks_id) REFERENCES truck(id)
);
END
GO

-- Insertar datos en las tablas
INSERT INTO app_user (created_at, email, firstname, fullname, lastname, password, role, updated_at)
VALUES
    ('2024-06-26 12:25:39.000000', 'admin@mail.com', 'Admin', 'Admin Admin', 'Admin', '$2b$10$UplCAmSIPSN3YQhDPJe88eG048qhUGIFPwxHULI7zcpcYovWtExoS', 'ADMIN', NULL);

INSERT INTO customer (email, name, phone) VALUES
                                              ('benjamin.clark@example.com', 'Benjamin Clark', '555-5678'),
                                              ('catherine.johnson@gmail.com', 'Catherine Johnson', '555-9101'),
                                              ('daniel.lewis@example.com', 'Daniel Lewis', '555-1121');

INSERT INTO store (location, name) VALUES
                                       ('Avenida Siempre Viva 123, Springfield', 'Tienda del Pueblo'),
                                       ('Calle Falsa 456, Shelbyville', 'Patito Store 2'),
                                       ('Boulevard del Bosque 789, Capital City', 'Patito Store 3');

INSERT INTO seller (name, store_id) VALUES
                                        ('John Doe', 2),
                                        ('Jane Smith', 1),
                                        ('Sarah Wilson', 3);

INSERT INTO truck (brand, discount, model, name, price, stock) VALUES
                                                                   ('HAWA', 1000.00, 'F1', 'Falcon', 10000.00, 6),
                                                                   ('HAWA', 1500.00, 'M1', 'Mustang', 15000.00, 0),
                                                                   ('HAWA', 2000.00, 'R1', 'Raptor', 20000.00, 1),
                                                                   ('HAWA', 2500.00, 'T1', 'Titan', 25000.00, 0);

INSERT INTO orders (order_date, status, total_price, customer_id, seller_id, store_id) VALUES
                                                                                           ('2024-06-24 21:39:32.592131', 'PENDING', 10000.00, 2, 2, 1),
                                                                                           ('2024-06-25 16:41:51.829042', 'PENDING', 25000.00, 2, 1, 2);


INSERT INTO orders_trucks (orders_id, trucks_id) VALUES
                                                     (1, 1),
                                                     (2, 4);