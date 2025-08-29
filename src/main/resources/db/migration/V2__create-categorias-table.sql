CREATE TABLE categorias (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    valorPlanejado DECIMAL(10, 2) NOT NULL CHECK (valorPlanejado >= 0),
    idUsuario UUID NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,

    FOREIGN KEY (idUsuario) REFERENCES usuarios(id)
);