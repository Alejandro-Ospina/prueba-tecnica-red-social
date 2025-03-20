-- Creating additional data on table users
INSERT INTO users (name, surname, age, country, email, pass, active, birthdate) VALUES
('Alejandro', 'Gomez', 25, 'Mexico', 'alejandro@example.com', '$2a$10$Hzr/v31uStEu/TDlWAgmk.5vPqXmrIooL9PxJ5Y6dzPNMLhHVWLMC', TRUE, '1999-05-21'),
('Maria', 'Lopez', 30, 'Argentina', 'maria@example.com', '$2a$10$Hzr/v31uStEu/TDlWAgmk.5vPqXmrIooL9PxJ5Y6dzPNMLhHVWLMC', TRUE, '1994-08-12'),
('Carlos', 'Fernandez', 27, 'España', 'carlos@example.com', '$2a$10$Hzr/v31uStEu/TDlWAgmk.5vPqXmrIooL9PxJ5Y6dzPNMLhHVWLMC', TRUE, '1997-11-05'),
('Sofia', 'Martinez', 22, 'Colombia', 'sofia@example.com', '$2a$10$Hzr/v31uStEu/TDlWAgmk.5vPqXmrIooL9PxJ5Y6dzPNMLhHVWLMC', TRUE, '2002-02-10'),
('Javier', 'Torres', 35, 'Chile', 'javier@example.com', '$2a$10$Hzr/v31uStEu/TDlWAgmk.5vPqXmrIooL9PxJ5Y6dzPNMLhHVWLMC', TRUE, '1989-07-19'),
('Lucia', 'Diaz', 29, 'Peru', 'lucia@example.com', '$2a$10$Hzr/v31uStEu/TDlWAgmk.5vPqXmrIooL9PxJ5Y6dzPNMLhHVWLMC', TRUE, '1995-04-03'),
('Fernando', 'Perez', 31, 'Ecuador', 'fernando@example.com', '$2a$10$Hzr/v31uStEu/TDlWAgmk.5vPqXmrIooL9PxJ5Y6dzPNMLhHVWLMC', TRUE, '1993-09-14'),
('Elena', 'Vargas', 28, 'Venezuela', 'elena@example.com', '$2a$10$Hzr/v31uStEu/TDlWAgmk.5vPqXmrIooL9PxJ5Y6dzPNMLhHVWLMC', TRUE, '1996-06-23'),
('Daniel', 'Ramirez', 26, 'Uruguay', 'daniel@example.com', '$2a$10$Hzr/v31uStEu/TDlWAgmk.5vPqXmrIooL9PxJ5Y6dzPNMLhHVWLMC', TRUE, '1998-12-30'),
('Paola', 'Ortega', 24, 'Bolivia', 'paola@example.com', '$2a$10$Hzr/v31uStEu/TDlWAgmk.5vPqXmrIooL9PxJ5Y6dzPNMLhHVWLMC', TRUE, '2000-03-15');

-- Inserting additional data on table publications
INSERT INTO publications (creation_date, topic, content, likes, user_id) VALUES
('2024-03-01 10:30:00', 'Tecnología', 'El futuro de la inteligencia artificial es prometedor.', 15, 1),
('2024-03-02 15:45:00', 'Cine', 'Reseña de la última película de ciencia ficción.', 22, 1),
('2024-03-03 08:20:00', 'Salud', 'Beneficios del ejercicio diario.', 9, 1),
('2024-03-04 12:10:00', 'Viajes', 'Los mejores destinos para visitar en 2025.', 30, 1),
('2024-03-05 18:05:00', 'Cocina', 'Receta fácil para hacer pan casero.', 18, 1),

('2024-03-06 09:40:00', 'Historia', 'Curiosidades sobre el Imperio Romano.', 12, 2),
('2024-03-07 14:30:00', 'Negocios', 'Estrategias para emprender con éxito.', 25, 2),
('2024-03-08 17:50:00', 'Ciencia', 'Últimos avances en exploración espacial.', 27, 2),
('2024-03-09 11:15:00', 'Educación', 'Cómo mejorar el aprendizaje en línea.', 14, 2),
('2024-03-10 20:00:00', 'Deportes', 'Análisis de la final de la Champions League.', 35, 2),

('2024-03-11 13:25:00', 'Música', 'El impacto de la música en la concentración.', 20, 3),
('2024-03-12 08:45:00', 'Libros', 'Reseña de la última novela de ficción.', 17, 3),
('2024-03-13 19:10:00', 'Cine', 'Las mejores películas de los últimos 10 años.', 23, 3),
('2024-03-14 10:55:00', 'Moda', 'Tendencias de ropa para la próxima temporada.', 5, 3),
('2024-03-15 16:35:00', 'Psicología', 'Cómo gestionar el estrés en el trabajo.', 31, 3),

('2024-03-16 07:20:00', 'Ecología', 'El cambio climático y sus efectos en el mundo.', 10, 4),
('2024-03-17 15:00:00', 'Astronomía', 'Descubrimientos recientes sobre exoplanetas.', 19, 4),
('2024-03-18 09:55:00', 'Política', 'Análisis del nuevo gobierno en América Latina.', 12, 4),
('2024-03-19 14:25:00', 'Economía', 'Impacto de la inflación en el mercado actual.', 21, 4),
('2024-03-20 18:40:00', 'Deportes', 'Los mejores atletas olímpicos de la historia.', 27, 4),

('2024-03-21 11:30:00', 'Gastronomía', 'Los secretos de la cocina francesa.', 24, 5),
('2024-03-22 16:15:00', 'Tecnología', 'Cómo la realidad aumentada cambiará nuestras vidas.', 33, 5),
('2024-03-23 20:50:00', 'Fotografía', 'Los mejores consejos para capturar paisajes.', 14, 5),
('2024-03-24 09:10:00', 'Educación', 'El impacto del homeschooling en los niños.', 19, 5),
('2024-03-25 13:40:00', 'Psicología', 'Cómo mejorar la autoestima en la adolescencia.', 29, 5),

('2024-03-26 12:05:00', 'Criptomonedas', '¿Es buen momento para invertir en Bitcoin?', 41, 6),
('2024-03-27 08:55:00', 'Negocios', 'Claves para una startup exitosa.', 18, 6),
('2024-03-28 17:30:00', 'Historia', 'Eventos históricos que marcaron el siglo XX.', 23, 6),
('2024-03-29 14:45:00', 'Arte', 'El renacimiento italiano y sus principales exponentes.', 30, 6),
('2024-03-30 10:25:00', 'Música', 'La evolución del jazz en América.', 21, 6),

('2024-03-31 18:15:00', 'Tecnología', 'La revolución de la inteligencia artificial.', 38, 7),
('2024-04-01 07:50:00', 'Cine', 'Películas de culto que debes ver.', 16, 7),
('2024-04-02 13:20:00', 'Viajes', 'Destinos exóticos para mochileros.', 14, 7),
('2024-04-03 15:55:00', 'Salud', 'Beneficios del ayuno intermitente.', 25, 7),
('2024-04-04 08:40:00', 'Cocina', 'Recetas saludables y fáciles.', 20, 7),

('2024-04-05 14:05:00', 'Filosofía', '¿Qué significa realmente la felicidad?', 12, 8),
('2024-04-06 17:20:00', 'Psicología', 'Cómo superar la ansiedad en tiempos de crisis.', 34, 8),
('2024-04-07 09:45:00', 'Política', 'Los cambios en el mapa político global.', 27, 8),
('2024-04-08 12:35:00', 'Ciencia', 'Descubrimientos recientes en biotecnología.', 19, 8),
('2024-04-09 16:50:00', 'Literatura', 'Los mejores libros de la década.', 23, 8),

('2024-04-10 10:15:00', 'Negocios', 'Cómo construir una marca personal exitosa.', 31, 9),
('2024-04-11 19:30:00', 'Cine', 'Películas ganadoras del Oscar y su impacto cultural.', 22, 9),
('2024-04-12 08:25:00', 'Deportes', 'Las mejores estrategias para mejorar el rendimiento físico.', 18, 9),
('2024-04-13 14:55:00', 'Tecnología', 'Avances en inteligencia artificial aplicada.', 29, 9),
('2024-04-14 17:40:00', 'Música', 'Cómo la música afecta nuestras emociones.', 17, 9),

('2024-04-15 11:10:00', 'Educación', 'El impacto de la tecnología en el aprendizaje.', 36, 10),
('2024-04-16 08:45:00', 'Historia', 'La caída del Imperio Romano: causas y consecuencias.', 19, 10),
('2024-04-17 13:20:00', 'Ciencia', 'El futuro de la exploración espacial.', 21, 10),
('2024-04-18 15:35:00', 'Psicología', 'Cómo desarrollar hábitos positivos.', 25, 10),
('2024-04-19 09:50:00', 'Viajes', 'Los destinos más visitados del mundo.', 27, 10);
