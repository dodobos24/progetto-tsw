DROP DATABASE IF EXISTS mytickez;
CREATE DATABASE mytickez;
USE mytickez;

DROP TABLE IF EXISTS Users;
CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    saldo FLOAT NOT NULL DEFAULT 0,
    admin TINYINT(1) NOT NULL
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
    event_type VARCHAR(50) NOT NULL,
    organizer VARCHAR(100) NOT NULL,
    price FLOAT NOT NULL,
    artist_id INT,
    image VARCHAR(255),
    FOREIGN KEY (artist_id) REFERENCES Artists(artist_id)
);

DROP TABLE IF EXISTS Tickets;
CREATE TABLE Tickets (
    ticket_id INT AUTO_INCREMENT PRIMARY KEY,
    event_id INT,
    user_id INT,
    purchase_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (event_id) REFERENCES Events(event_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

DROP TABLE IF EXISTS Payments;
CREATE TABLE Payments (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    ticket_id INT NOT NULL,
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
    FOREIGN KEY (cart_id) REFERENCES Carts(cart_id),
    FOREIGN KEY (event_id) REFERENCES Events(event_id) ON DELETE SET NULL
);

-- Popolazione artisti
INSERT INTO Artists (artist_name, genre) VALUES
('Drake', 'Trap'),
('Travis Scott', 'Rap'),
('Blanco', 'Pop');

-- Popolazione eventi
INSERT INTO Events (event_name, event_date, venue, description, event_type, organizer, price, artist_id, image) VALUES
-- Eventi musicali
('Concerto di Drake', '2024-07-15 20:00:00', 'Stadio Olimpico', 'Concerto di Drake.', 'Musica', 'Rock Productions', 10, 1, NULL),
('Concerto di Travis Scott', '2024-07-20 21:00:00', 'Arena Centrale', 'Concerto di Travis Scott.', 'Musica', 'Pop Events Inc.', 15, 2, NULL),
('Concerto di Blanco', '2024-07-25 22:00:00', 'Club Notturno', 'Concerto di Blanco.', 'Musica', 'Electronic Nights', 20, 3, NULL),

-- Festival
('Festival del Cinema', '2024-08-05 18:00:00', 'Cinema Paradiso', 'Proiezione di film indipendenti.', 'Festival', 'CineFest Organizers', 10, NULL, NULL),
('Festival di Cibo', '2024-08-10 12:00:00', 'Parco Cittadino', 'Giornata dedicata alla degustazione di cibi locali.', 'Festival', 'Food Lovers', 15, NULL, NULL),
('Festival della Musica', '2024-08-15 14:00:00', 'Spiaggia Grande', 'Musica dal vivo sulla spiaggia.', 'Festival', 'Beach Sound', 20, NULL, NULL),

-- Teatro
('Spettacolo Teatrale', '2024-09-10 19:30:00', 'Teatro Nazionale', 'Una nuova produzione teatrale.', 'Teatro', 'Theater Group', 10, NULL, NULL),
('Commedia Classica', '2024-09-15 20:00:00', 'Teatro Classico', 'Rappresentazione di una famosa commedia.', 'Teatro', 'Classic Plays', 15, NULL, NULL),
('Dramma Contemporaneo', '2024-09-20 19:00:00', 'Teatro Moderno', 'Un dramma moderno su temi attuali.', 'Teatro', 'Modern Drama Inc.', 20, NULL, NULL),

-- Arte
('Mostra d\'Arte Moderna', '2024-10-15 10:00:00', 'Galleria d\'Arte Moderna', 'Mostra delle opere di artisti contemporanei.', 'Arte', 'Art Exhibition Inc.', 10, NULL, NULL),
('Esposizione di Sculture', '2024-10-20 11:00:00', 'Museo delle Sculture', 'Esposizione delle migliori sculture.', 'Arte', 'Sculpture Showcase', 15, NULL, NULL),
('Mostra di Fotografia', '2024-10-25 09:00:00', 'Centro Fotografico', 'Mostra delle fotografie premiate.', 'Arte', 'Photo Art Gallery', 20, NULL, NULL),

-- Sport
('Partita di Calcio', '2024-11-20 15:00:00', 'Stadio Comunale', 'Partita del campionato di calcio.', 'Sport', 'Sports League', 10, NULL, NULL),
('Torneo di Tennis', '2024-11-25 16:00:00', 'Centro Tennis', 'Torneo di tennis internazionale.', 'Sport', 'Tennis World', 15, NULL, NULL),
('Gara di Atletica', '2024-11-30 10:00:00', 'Stadio Olimpico', 'Gara di atletica con partecipanti internazionali.', 'Sport', 'Athletics Association', 20, NULL, NULL);

