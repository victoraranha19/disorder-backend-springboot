CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS categorias (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    valorPlanejado DECIMAL(10, 2) NOT NULL CHECK (valorMes >= 0),
    idUsuario INTEGER NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,

    FOREIGN KEY (idUsuario) REFERENCES usuarios(id)
);