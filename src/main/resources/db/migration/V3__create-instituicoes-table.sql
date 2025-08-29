CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS instituicoes (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    idUsuario UUID NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,

    FOREIGN KEY (idUsuario) REFERENCES usuarios(id)
);