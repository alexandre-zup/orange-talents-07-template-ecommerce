CREATE TABLE pergunta_produto (
    id bigint NOT NULL auto_increment,
    criada_em datetime(6) NOT NULL,
    titulo varchar(255) NOT NULL,
    produto_id bigint NOT NULL,
    usuario_id bigint NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_perguntaproduto_produtoid FOREIGN KEY (produto_id) REFERENCES produto(id),
    CONSTRAINT fk_perguntaproduto_usuarioid FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);