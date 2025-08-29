CREATE TABLE transacoes (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    descricao VARCHAR(255)  NOT NULL,
    valor DECIMAL(10, 2) NOT NULL CHECK (valor > 0),
    dataTransacao DATE NOT NULL,
    tipo VARCHAR(1) NOT NULL,
    parcelas INTEGER NOT NULL CHECK (valor > 0),
    idUsuario UUID NOT NULL,
    idCategoria UUID,
    idConta UUID,
    ativo BOOLEAN DEFAULT TRUE,

    FOREIGN KEY (idCategoria) REFERENCES categorias(id),
    FOREIGN KEY (idConta) REFERENCES contas(id),
    FOREIGN KEY (idUsuario) REFERENCES usuarios(id)
);