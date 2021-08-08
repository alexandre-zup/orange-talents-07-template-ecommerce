CREATE TABLE compra (
    id bigint NOT NULL auto_increment,
    gateway_pagamento VARCHAR(255) NOT NULL,
    quantidade_comprada INTEGER NOT NULL,
    status VARCHAR(255) NOT NULL,
    valor_no_momento_da_compra DECIMAL(19,2) NOT NULL,
    comprador_id bigint NOT NULL,
    produto_id bigint NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_compra_usuarioid FOREIGN KEY (comprador_id) REFERENCES usuario(id),
    CONSTRAINT fk_compra_produtoid FOREIGN KEY (produto_id) REFERENCES produto(id)
);
