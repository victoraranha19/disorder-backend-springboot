CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE usuarios (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    username VARCHAR(30) NOT NULL UNIQUE,
    senha VARCHAR(30) NOT NULL,
    nomeCompleto VARCHAR(30) NOT NULL,
    email VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    chavePix VARCHAR(255),
    idAcessor UUID CHECK (idAcessor IS NULL OR idAcessor <> id),
    ativo BOOLEAN DEFAULT TRUE,

    FOREIGN KEY (idAcessor) REFERENCES usuarios(id)
);