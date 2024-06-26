drop database if exists mytickez;
create database mytickez;
use mytickez;

DROP TABEL IF EXISTS Users;
CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    admin tinyint(1) NOT NULL,
);

DROP TABLE IF EXISTS Artists;
CREATE TABLE Artists (
    artist_id INT AUTO_INCREMENT PRIMARY KEY,
    artist_name VARCHAR(100) NOT NULL,
    genre VARCHAR(50)
);

DROP TABLE IF EXISTS Events;
CREATE TABLE Events (
    event_id INT AUTO_INCREMENT PRIMARY KEY,
    event_name VARCHAR(100) NOT NULL,
    event_date DATETIME NOT NULL,
    venue VARCHAR(100) NOT NULL,
    description TEXT,
    event_type VARCHAR(50) not null,  -- Attributo per il tipo di evento
    organizer VARCHAR(100) not null,  -- Attributo per l'organizzatore dell'evento
    artist_id INT,  -- Attributo per l'artista (se applicabile)
    FOREIGN KEY (artist_id) REFERENCES Artists(artist_id)  -- Chiave esterna verso Artists
);

DROP TABLE IF EXISTS Tickets;
CREATE TABLE Tickets (
    ticket_id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT,
    user_id INT,
    purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    seat_number VARCHAR(10),
    price DECIMAL(10, 2),
    FOREIGN KEY (event_id) REFERENCES Events(event_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

DROP TABLE IF EXISTS Payments;
CREATE TABLE Payments (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT not null,
    ticket_id INT not null,
    amount DECIMAL(10, 2) NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    payment_method VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (ticket_id) REFERENCES Tickets(ticket_id)
);

DROP TABLE IF EXISTS Carts;
CREATE TABLE Carts (
    cart_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

DROP TABLE IF EXISTS CartItems;
CREATE TABLE CartItems (
    cart_item_id INT AUTO_INCREMENT PRIMARY KEY,
    cart_id INT,
    event_id INT,
    quantity INT,
    seat_number VARCHAR(10),
    price DECIMAL(10, 2),
    FOREIGN KEY (cart_id) REFERENCES Carts(cart_id),
    FOREIGN KEY (event_id) REFERENCES Events(event_id)
);