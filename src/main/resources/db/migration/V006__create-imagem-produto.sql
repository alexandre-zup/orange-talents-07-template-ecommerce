CREATE TABLE imagem_produto (
    produto_id bigint NOT NULL,
    url VARCHAR(255) NOT NULL,
    PRIMARY KEY (produto_id, url),
    CONSTRAINT fk_imagem_produto_produto_id FOREIGN KEY (produto_id) REFERENCES produto(id)
);