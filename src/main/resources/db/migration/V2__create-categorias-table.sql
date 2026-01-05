CREATE TABLE categorias (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    titulo VARCHAR(50) NOT NULL,
    valor_planejado DECIMAL(10, 2) NOT NULL CHECK (valor_planejado >= 0),
    id_usuario UUID,
    ativo BOOLEAN DEFAULT TRUE,

    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);