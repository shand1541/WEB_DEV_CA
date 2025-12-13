-- ========================================
CA2 Database Setup Script
-- ========================================

-- Create database 
CREATE DATABASE ecommerce_ca2;
USE ecommerce_ca2;

-- Members table 
CREATE TABLE members (
    member_id INT AUTO_INCREMENT PRIMARY KEY,
    login_name VARCHAR(45) UNIQUE NOT NULL,
    email_address VARCHAR(95) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    display_name VARCHAR(95) NOT NULL,
    contact_number VARCHAR(18),
    postal_address TEXT,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    account_status ENUM('active', 'suspended', 'inactive') DEFAULT 'active' --set as active when signed up 
);
-- Products table
CREATE TABLE products (
    product_id INT AUTO_INCREMENT PRIMARY KEY, -- PK ID key 
    product_name VARCHAR(180) NOT NULL,
    product_details TEXT,
    product_category VARCHAR(45),
    minimum_bid DECIMAL(10,2) NOT NULL,
    highest_bid DECIMAL(10,2) DEFAULT 0.00,
    owner_id INT NOT NULL,
    listing_status ENUM('available', 'completed', 'cancelled') DEFAULT 'available', -- set available when listed 
    listing_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,//time listed 
    auction_end TIMESTAMP,//auction time ended
    image_path VARCHAR(255),
    FOREIGN KEY (owner_id) REFERENCES members(member_id) --FK to link to members table 
);
-- Bidding table
CREATE TABLE bidding_history (
    bid_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    member_id INT NOT NULL, --bidders id 
    bid_value DECIMAL(10,2) NOT NULL,
    bid_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    bid_status ENUM('active', 'outbid', 'winning') DEFAULT 'active',
    FOREIGN KEY (product_id) REFERENCES products(product_id),-- FK to link to products table
    FOREIGN KEY (member_id) REFERENCES members(member_id) --FK to link to members table
);

-- Sample data input 
INSERT INTO members (login_name, email_address, password_hash, display_name, contact_number, postal_address) VALUES
('alice_trader', 'alice@marketplace.com', 'secure123', 'Alice Johnson', '555-0101', '789 Elm Street, City A'),
('bob_collector', 'bob@marketplace.com', 'secure456', 'Bob Williams', '555-0202', '321 Pine Road, City B'),
('carol_seller', 'carol@marketplace.com', 'secure789', 'Carol Davis', '555-0303', '654 Maple Ave, City C');

INSERT INTO products (product_name, product_details, product_category, minimum_bid, highest_bid, owner_id, auction_end) VALUES
('Antique Pocket Watch', 'Beautiful gold-plated pocket watch from the 1920s, fully functional with original chain', 'Antiques', 85.00, 120.00, 1, DATE_ADD(NOW(), INTERVAL 10 DAY)),
('Gaming Laptop', 'High-performance laptop perfect for gaming and development work, barely used', 'Electronics', 450.00, 580.00, 2, DATE_ADD(NOW(), INTERVAL 6 DAY)),
('Handmade Ceramic Vase', 'Unique ceramic vase created by local artist, perfect centerpiece', 'Art', 25.00, 35.00, 3, DATE_ADD(NOW(), INTERVAL 8 DAY));

INSERT INTO bidding_history (product_id, member_id, bid_value, bid_status) VALUES
(1, 2, 95.00, 'outbid'),
(1, 3, 120.00, 'winning'),
(2, 1, 480.00, 'outbid'),
(2, 3, 580.00, 'winning'),
(3, 1, 30.00, 'outbid'),
(3, 2, 35.00, 'winning');
