CREATE TABLE transacoes (
    id SERIAL DEFAULT generate_series() PRIMARY KEY,
    descricao VARCHAR(255)  NOT NULL,
    valor DOUBLE PRECISION NOT NULL CHECK (valor > 0),
    data_transacao DATE NOT NULL,
    tipo VARCHAR(1) NOT NULL,
    parcelas INTEGER NOT NULL CHECK (valor > 0) DEFAULT 1,
    id_usuario UUID,
    id_categoria INTEGER,
    id_carteira INTEGER,
    ativo BOOLEAN DEFAULT TRUE,

    FOREIGN KEY (id_categoria) REFERENCES categorias(id),
    FOREIGN KEY (id_carteira) REFERENCES carteiras(id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);