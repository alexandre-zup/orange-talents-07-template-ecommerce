CREATE TABLE produto (
    id bigint NOT NULL auto_increment,
    criado_em datetime(6) NOT NULL,
    descricao VARCHAR(1000) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    quantidade INTEGER NOT NULL,
    valor DECIMAL(19,2) NOT NULL,
    categoria_id bigint NOT NULL,
    usuario_id bigint NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_produto_catedoria_id FOREIGN KEY (categoria_id) REFERENCES  categoria(id),
    CONSTRAINT fk_produto_usuario_id FOREIGN KEY (usuario_id) REFERENCES  usuario(id)
);


CREATE TABLE caracteristica_produto (
    produto_id bigint NOT NULL,
    descricao_caracteristica VARCHAR(255) NOT NULL,
    nome_caracterisica VARCHAR(255) NOT NULL,
    PRIMARY KEY (produto_id, nome_caracterisica),
    CONSTRAINT fk_caracteristica_produto_produto_id FOREIGN KEY (produto_id) REFERENCES produto(id)
);