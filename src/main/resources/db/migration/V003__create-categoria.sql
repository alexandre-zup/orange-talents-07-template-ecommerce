CREATE TABLE categoria (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    categoria_mae_id BIGINT,
    PRIMARY KEY(id),
    CONSTRAINT fk_categoria_mae FOREIGN KEY(categoria_mae_id) REFERENCES categoria(id),
    CONSTRAINT uk_categoria_nome UNIQUE (nome)
);