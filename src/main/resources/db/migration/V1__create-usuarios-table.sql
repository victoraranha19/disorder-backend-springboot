CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE usuarios (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    username VARCHAR(30) NOT NULL UNIQUE,
    senha VARCHAR(30) NOT NULL,
    nome_completo VARCHAR(30) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefone VARCHAR(20),
    chave_pix VARCHAR(255),
    papel VARCHAR(255),
    id_acessor UUID CHECK (id_acessor IS NULL OR id_acessor <> id),
    ativo BOOLEAN DEFAULT TRUE,

    FOREIGN KEY (idAcessor) REFERENCES usuarios(id)
);