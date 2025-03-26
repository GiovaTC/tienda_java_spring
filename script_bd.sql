CREATE DATABASE tienda;

USE tienda;

CREATE TABLE productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    tipo VARCHAR(100) NOT NULL,
    fecha_fabricacion DATE NOT NULL,
    fecha_vencimiento DATE NOT NULL,
    imagen VARCHAR(255) NOT NULL
);

INSERT INTO productos (nombre, tipo, fecha_fabricacion, fecha_vencimiento, imagen) VALUES
('Leche', 'Lácteo', '2025-01-01', '2025-06-01', 'leche.jpg'),
('Pan', 'Panadería', '2025-03-15', '2025-04-01', 'pan.jpg'),
('Queso', 'Lácteo', '2025-02-20', '2025-05-20', 'queso.jpg'),
('Jabón', 'Limpieza', '2025-01-10', '2026-01-10', 'jabon.jpg'),
('Arroz', 'Cereal', '2025-02-01', '2026-02-01', 'arroz.jpg');
