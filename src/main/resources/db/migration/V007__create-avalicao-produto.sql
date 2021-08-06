 CREATE TABLE avalicao_produto (
    id bigint NOT NULL auto_increment,
    descricao VARCHAR(500) NOT NULL,
    nota INTEGER NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    produto_id bigint NOT NULL,
    usuario_id bigint NOT NULL,
    primary key (id),
    CONSTRAINT fk_avalicao_produto_produto_id FOREIGN KEY (produto_id) REFERENCES produto(id),
    CONSTRAINT fk_avalicao_produto_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);
