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
    event_type VARCHAR(50) not null,
    organizer VARCHAR(100) not null,
    artist_id INT,
    FOREIGN KEY (artist_id) REFERENCES Artists(artist_id)
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
<<<<<<< HEAD
);
=======
);

--popolazione artisti
INSERT INTO Artists (artist_name, genre) VALUES
('Drake', 'Trap'),
('Travis Scott', 'Rap'),
('Blanco', 'Pop');

--popolazione eventi
INSERT INTO Events (event_name, event_date, venue, description, event_type, organizer, artist_id) VALUES
--Eventi musicali
('Concerto di Rock', '2024-07-15 20:00:00', 'Stadio Olimpico', 'Un grande concerto di rock con band famose.', 'Musica', 'Rock Productions', 1),
('Concerto Pop', '2024-07-20 21:00:00', 'Arena Centrale', 'Concerto con la famosa Cantante Pop.', 'Musica', 'Pop Events Inc.', 2),
('Festa Elettronica', '2024-07-25 22:00:00', 'Club Notturno', 'Serata di musica elettronica con DJ Electro.', 'Musica', 'Electronic Nights', 3),

-- Festival
('Festival del Cinema', '2024-08-05 18:00:00', 'Cinema Paradiso', 'Proiezione di film indipendenti.', 'Festival', 'CineFest Organizers', NULL),
('Festival di Cibo', '2024-08-10 12:00:00', 'Parco Cittadino', 'Giornata dedicata alla degustazione di cibi locali.', 'Festival', 'Food Lovers', NULL),
('Festival della Musica', '2024-08-15 14:00:00', 'Spiaggia Grande', 'Musica dal vivo sulla spiaggia.', 'Festival', 'Beach Sound', NULL),

-- Teatro
('Spettacolo Teatrale', '2024-09-10 19:30:00', 'Teatro Nazionale', 'Una nuova produzione teatrale.', 'Teatro', 'Theater Group', NULL),
('Commedia Classica', '2024-09-15 20:00:00', 'Teatro Classico', 'Rappresentazione di una famosa commedia.', 'Teatro', 'Classic Plays', NULL),
('Dramma Contemporaneo', '2024-09-20 19:00:00', 'Teatro Moderno', 'Un dramma moderno su temi attuali.', 'Teatro', 'Modern Drama Inc.', NULL),

-- Arte
('Mostra d\'Arte Moderna', '2024-10-15 10:00:00', 'Galleria d\'Arte Moderna', 'Mostra delle opere di artisti contemporanei.', 'Arte', 'Art Exhibition Inc.', NULL),
('Esposizione di Sculture', '2024-10-20 11:00:00', 'Museo delle Sculture', 'Esposizione delle migliori sculture.', 'Arte', 'Sculpture Showcase', NULL),
('Mostra di Fotografia', '2024-10-25 09:00:00', 'Centro Fotografico', 'Mostra delle fotografie premiate.', 'Arte', 'Photo Art Gallery', NULL),

-- Sport
('Partita di Calcio', '2024-11-20 15:00:00', 'Stadio Comunale', 'Partita del campionato di calcio.', 'Sport', 'Sports League', NULL),
('Torneo di Tennis', '2024-11-25 16:00:00', 'Centro Tennis', 'Torneo di tennis internazionale.', 'Sport', 'Tennis World', NULL),
('Gara di Atletica', '2024-11-30 10:00:00', 'Stadio Olimpico', 'Gara di atletica con partecipanti internazionali.', 'Sport', 'Athletics Association', NULL);

);

>>>>>>> branch 'main' of https://github.com/dodobos24/progetto-tsw.git
