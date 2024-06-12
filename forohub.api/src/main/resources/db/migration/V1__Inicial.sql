-- Crear tabla Usuario
CREATE TABLE Usuario (
    idUsuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Crear tabla Perfil
CREATE TABLE Perfil (
    idPerfil BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

-- Crear tabla Curso
CREATE TABLE Curso (
    idCurso BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL
);

-- Crear tabla Topico
CREATE TABLE Topico (
    idTopico BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fechaCreacion TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL,
    autor BIGINT NOT NULL,
    curso BIGINT NOT NULL,
    FOREIGN KEY (autor) REFERENCES Usuario(idUsuario),
    FOREIGN KEY (curso) REFERENCES Curso(idCurso)
);

-- Crear tabla Respuesta
CREATE TABLE Respuesta (
    idRespuesta BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensaje TEXT NOT NULL,
    topico BIGINT NOT NULL,
    fechaCreacion TIMESTAMP NOT NULL,
    autor BIGINT NOT NULL,
    solucion BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (topico) REFERENCES Topico(idTopico),
    FOREIGN KEY (autor) REFERENCES Usuario(idUsuario)
);

-- Crear tabla UsuarioPerfil
CREATE TABLE UsuarioPerfil (
    idUsuario BIGINT NOT NULL,
    idPerfil BIGINT NOT NULL,
    PRIMARY KEY (idUsuario, idPerfil),
    FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario),
    FOREIGN KEY (idPerfil) REFERENCES Perfil(idPerfil)
);
