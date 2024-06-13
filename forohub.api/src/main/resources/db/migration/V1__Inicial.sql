-- Create table usuario
CREATE TABLE usuario (
    id_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Create table perfil
CREATE TABLE perfil (
    id_perfil BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

-- Create table curso
CREATE TABLE curso (
    id_curso BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL
);

-- Create table topico
CREATE TABLE topico (
    id_topico BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL,
    status VARCHAR(50) NOT NULL,
    autor BIGINT NOT NULL,
    curso BIGINT NOT NULL,
    FOREIGN KEY (autor) REFERENCES usuario(id_usuario),
    FOREIGN KEY (curso) REFERENCES curso(id_curso)
);

-- Create table respuesta
CREATE TABLE respuesta (
    id_respuesta BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensaje TEXT NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL,
    solucion BOOLEAN DEFAULT FALSE,
    topico BIGINT NOT NULL,
    autor BIGINT NOT NULL,
    FOREIGN KEY (topico) REFERENCES topico(id_topico),
    FOREIGN KEY (autor) REFERENCES usuario(id_usuario)
);

-- Create table usuario_perfil
CREATE TABLE usuario_perfil (
    id_usuario BIGINT NOT NULL,
    id_perfil BIGINT NOT NULL,
    PRIMARY KEY (id_usuario, id_perfil),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
    FOREIGN KEY (id_perfil) REFERENCES perfil(id_perfil)
);
