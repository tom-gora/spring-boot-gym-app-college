CREATE DATABASE IF NOT EXISTS gym_app_demo;

USE gym_app_demo;

CREATE TABLE IF NOT EXISTS members (
    member_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    membership_type ENUM('GM', 'SM', 'CM') NOT NULL,
    registration_date DATE NOT NULL
);

INSERT INTO members (first_name, last_name, membership_type, registration_date)
VALUES 
    ('Kael', 'Duskwither', 'GM', '2024-01-01'),
    ('Elara', 'Vyrn', 'SM', '2024-01-15'),
    ('Seraphina', 'Morn', 'CM', '2024-02-01'),
    ('Thaddeus', 'Ashenvale', 'GM', '2024-03-01'),
    ('Aurora', 'Dawnspire', 'SM', '2024-03-15'),
    ('Cassius', 'Shadeborn', 'CM', '2024-04-01'),
    ('Lysandra', 'Frostwhisper', 'GM', '2024-04-15'),
    ('Draven', 'Ironclaw', 'SM', '2024-05-01'),
    ('Isolde', 'Leafmorn', 'CM', '2024-05-15'),
    ('Jareth', 'Stonefist', 'GM', '2024-06-01'),
    ('Morgana', 'Shadowleaf', 'SM', '2024-06-15'),
    ('Rory', 'Moonsong', 'CM', '2024-07-01');
