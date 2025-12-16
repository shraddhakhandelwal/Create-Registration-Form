-- Database Schema for User Registration System
-- This script creates the users table with all necessary constraints

-- Create database (if using MySQL directly)
CREATE DATABASE IF NOT EXISTS registration_db;
USE registration_db;

-- Drop table if exists (for fresh installation)
DROP TABLE IF EXISTS users;

-- Create users table
CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    username VARCHAR(30) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    date_of_birth DATE NOT NULL,
    gender VARCHAR(10) NOT NULL,
    address VARCHAR(200),
    city VARCHAR(50),
    state VARCHAR(50),
    postal_code VARCHAR(6),
    country VARCHAR(50),
    is_active BOOLEAN DEFAULT TRUE NOT NULL,
    email_verified BOOLEAN DEFAULT FALSE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    -- Constraints
    CONSTRAINT chk_gender CHECK (gender IN ('Male', 'Female', 'Other')),
    CONSTRAINT chk_email_format CHECK (email LIKE '%@%'),
    CONSTRAINT chk_phone_length CHECK (LENGTH(phone_number) = 10),
    CONSTRAINT chk_postal_code CHECK (postal_code IS NULL OR LENGTH(postal_code) = 6)
);

-- Create indexes for better query performance
CREATE INDEX idx_email ON users(email);
CREATE INDEX idx_username ON users(username);
CREATE INDEX idx_city ON users(city);
CREATE INDEX idx_state ON users(state);
CREATE INDEX idx_is_active ON users(is_active);
CREATE INDEX idx_created_at ON users(created_at);

-- Insert sample data (optional - for testing)
INSERT INTO users (first_name, last_name, username, email, password, phone_number, 
                   date_of_birth, gender, address, city, state, postal_code, country) 
VALUES 
('John', 'Doe', 'johndoe', 'john.doe@example.com', 
 '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', -- password: password123
 '9876543210', '1990-01-15', 'Male', '123 Main Street', 'New York', 
 'NY', '110001', 'USA'),
 
('Jane', 'Smith', 'janesmith', 'jane.smith@example.com',
 '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', -- password: password123
 '9876543211', '1992-05-20', 'Female', '456 Oak Avenue', 'Los Angeles',
 'CA', '110002', 'USA');

-- Verify table creation
SELECT * FROM users;

-- Show table structure
DESCRIBE users;
