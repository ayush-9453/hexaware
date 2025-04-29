CREATE DATABASE pets;
USE pets;

CREATE TABLE Pet (
    pet_id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL CHECK (age > 0),
    breed VARCHAR(100),
    pet_type ENUM('Dog', 'Cat') NOT NULL,
    dog_breed VARCHAR(100),     -- Specific dog breed (if pet_type = Dog)
    cat_color VARCHAR(100)      -- Specific cat color (if pet_type = Cat)
);

CREATE TABLE adoption_events (
    event_id INT AUTO_INCREMENT PRIMARY KEY,
    event_name VARCHAR(100) NOT NULL,
    event_date DATETIME NOT NULL
);

CREATE TABLE donations (
    id INT PRIMARY KEY AUTO_INCREMENT,
    donor_name VARCHAR(100) NOT NULL,
    amount DOUBLE NOT NULL,
    donation_type VARCHAR(20) NOT NULL, 
    donation_date DATE,              
    item_type VARCHAR(100)             
);





