-- SQLite
DROP TABLE IF EXISTS contenidos;
DROP TABLE IF EXISTS usuarios;
DROP TABLE IF EXISTS reproducciones;

CREATE TABLE contenidos(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    titulo TEXT NOT NULL,
    descripcion TEXT NOT NULL,
    puntuacion INTEGER NOT NULL,
    genero TEXT,
    generoMusica TEXT,
    duracion INT,
    director TEXT,
    capitulos INT,
    temporadas INT,
    artista TEXT
);

CREATE TABLE usuarios(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre TEXT NOT NULL,
    email TEXT NOT NULL,
    contraseña TEXT NOT NULL,
    suscripcion TEXT NOT NULL
);

CREATE TABLE reproducciones (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    id_usuario INTEGER,
    id_contenido INTEGER,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    FOREIGN KEY (id_contenido) REFERENCES contenidos(id)
);

-- ||||||| CONTENIDOS ||||||| 
-- Insertar películas
INSERT INTO contenidos (titulo, descripcion, puntuacion, genero, duracion, director) 
VALUES ('Piratas del Caribe: La maldición de la Perla Negra', 'Aventura pirata con Jack Sparrow buscando un barco maldito', 5, 'ACCION', 143, 'Gore Verbinski');

INSERT INTO contenidos (titulo, descripcion, puntuacion, genero, duracion, director) 
VALUES ('Interestelar', 'Un grupo de astronautas viaja a través de un agujero de gusano en busca de un nuevo hogar para la humanidad.', 4, 'CIENCIA_FICCION', 169, 'Christopher Nolan');

INSERT INTO contenidos (titulo, descripcion, puntuacion, genero, duracion, director) 
VALUES ('Los Juegos del Hambre', 'Katniss lucha en un reality show mortal en un futuro distópico', 4, 'ACCION', 142, 'Gary Ross');

INSERT INTO contenidos (titulo, descripcion, puntuacion, genero, duracion, director) 
VALUES ('Transporter', 'Exmilitar transporta paquetes peligrosos con acción a alta velocidad', 3, 'ACCION', 92, 'Louis Leterrier');

INSERT INTO contenidos (titulo, descripcion, puntuacion, genero, duracion, director) 
VALUES ('Salvar al Soldado Ryan', 'Intensa guerra tras el Día-D para rescatar a un soldado', 5, 'BELICO', 169, 'Steven Spielberg');

-- Insertar series
INSERT INTO contenidos (titulo, descripcion, puntuacion, genero, duracion, capitulos, temporadas) 
VALUES ('Attack on Titan', 'Mundo habitado de titanes que comen personas.', 5, 'DRAMA', 88, 88, 4);

INSERT INTO contenidos (titulo, descripcion, puntuacion, genero, duracion, capitulos, temporadas) 
VALUES ('Death Note', 'Light, cansado de la delincuencia encuentra una libreta que mata personas con solo escribir su nombre.', 4, 'DRAMA', 37, 37, 1);

INSERT INTO contenidos (titulo, descripcion, puntuacion, genero, duracion, capitulos, temporadas) 
VALUES ('South Park', 'Comedia de situación que sigue la vida cotidiana de los niños del pueblo.', 4, 'COMEDIA', 327, 327, 26);

INSERT INTO contenidos (titulo, descripcion, puntuacion, genero, duracion, capitulos, temporadas) 
VALUES ('Doraemon', 'Gato robotico del futuro viene del futuro para ayudar a Nobita a que no se meta en problemas.', 5, 'COMEDIA', 1700, 1700, 40);

INSERT INTO contenidos (titulo, descripcion, puntuacion, genero, duracion, capitulos, temporadas) 
VALUES ('The Last Of Us', 'serie postapocalíptica de HBO donde Joel y Ellie viajan en un mundo infectado, mezclando acción, terror y drama emocional.', 0, 'ACCION', 9, 9, 1);

-- Insertar música
INSERT INTO contenidos (titulo, descripcion, puntuacion, generoMusica, duracion, artista) 
VALUES ('Don''t Stop The Music', 'Canción dance-pop de Rihanna, ritmo contagioso, inspirada en Michael Jackson.', 5, 'POP', 239, 'Rihanna');

INSERT INTO contenidos (titulo, descripcion, puntuacion, generoMusica, duracion, artista) 
VALUES ('California Gurls', 'Himno veraniego de Katy Perry con Snoop Dogg, celebra la diversión en la playa.', 5, 'POP', 236, 'Katy Perry ft. Snoop Dogg');

INSERT INTO contenidos (titulo, descripcion, puntuacion, generoMusica, duracion, artista) 
VALUES ('P.Y.T', 'Clásico de Michael Jackson, groove funk y coros pegajosos sobre el amor juvenil.', 5, 'POP', 239, 'Michael Jackson');

INSERT INTO contenidos (titulo, descripcion, puntuacion, generoMusica, duracion, artista) 
VALUES ('OIIA OIIA', 'Gato Giratorio', 5, 'ELECTRONICA', 134, 'W&W');

INSERT INTO contenidos (titulo, descripcion, puntuacion, generoMusica, duracion, artista) 
VALUES ('BADLOBA BREAKBEAT', 'Track electrónico enérgico con ritmos rotos y sonidos futuristas, ideal para el baile.', 4, 'HIP_HOP', 150, 'DJ Jilguero');

-- ||||||| USUARIOS |||||||
INSERT INTO usuarios (nombre, email, contraseña, suscripcion) 
VALUES ('user1', 'user1', 'user1', 'BASICA');

INSERT INTO usuarios (nombre, email, contraseña, suscripcion) 
VALUES ('user2', 'user2', 'user2', 'PREMIUM');

INSERT INTO usuarios (nombre, email, contraseña, suscripcion) 
VALUES ('a', 'a', 'a', 'PREMIUM_PLUS');

-- SUBCONSULTA
SELECT nombre AS usuario, titulo AS contenido, COUNT(*) AS veces_reproducido
FROM reproducciones
JOIN usuarios ON id_usuario = id
JOIN contenidos c ON id_contenido = id
GROUP BY id, id
ORDER BY nombre, veces_reproducido DESC;