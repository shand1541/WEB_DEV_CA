-- Create database
CREATE DATABASE IF NOT EXISTS ecommerce_ca2;
USE ecommerce_ca2;

-- Create members table
CREATE TABLE IF NOT EXISTS members (
    member_id INT AUTO_INCREMENT PRIMARY KEY,
    login_name VARCHAR(50) UNIQUE NOT NULL,
    email_address VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    display_name VARCHAR(100) NOT NULL,
    contact_number VARCHAR(20),
    postal_address TEXT,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    account_status ENUM('active', 'suspended', 'deleted') DEFAULT 'active'
);

-- Insert test user (password is 'password')
INSERT INTO members (login_name, email_address, password_hash, display_name, contact_number, postal_address) 
VALUES ('testuser', 'test@example.com', 'password', 'Test User', '123-456-7890', '123 Test Street, Test City');

-- Create products table 
CREATE TABLE IF NOT EXISTS products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    category VARCHAR(50),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('active', 'inactive', 'deleted') DEFAULT 'active'
);

-- Create bids table
CREATE TABLE IF NOT EXISTS bids (
    bid_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    member_id INT,
    bid_amount DECIMAL(10,2) NOT NULL,
    bid_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('active', 'withdrawn', 'accepted', 'rejected') DEFAULT 'active',
    FOREIGN KEY (product_id) REFERENCES products(product_id),
    FOREIGN KEY (member_id) REFERENCES members(member_id)
);

SELECT 'Database setup completed successfully!' as message;