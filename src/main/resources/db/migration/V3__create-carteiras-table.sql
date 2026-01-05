CREATE TABLE carteiras (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    titulo VARCHAR(50) NOT NULL,
    conta_corrente DOUBLE PRECISION NOT NULL DEFAULT 0,
    conta_poupanca DOUBLE PRECISION NOT NULL DEFAULT 0,
    conta_investimento DOUBLE PRECISION NOT NULL DEFAULT 0,
    limite_credito_total DOUBLE PRECISION NOT NULL DEFAULT 0,
    id_usuario UUID,
    ativo BOOLEAN DEFAULT TRUE,

    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);