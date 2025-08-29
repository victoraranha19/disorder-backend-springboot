CREATE TABLE contas (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    valorConta DECIMAL(10, 2) NOT NULL DEFAULT 0 CHECK (valorConta >= 0),
    tipo VARCHAR(1) NOT NULL,
    idInstituicoes UUID NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,

    FOREIGN KEY (idInstituicoes) REFERENCES instituicoes(id)
);