CREATE TABLE pagamento (
    id bigint NOT NULL auto_increment,
    gateway_pagamento VARCHAR(255) NOT NULL,
    id_pagamento_no_gateway VARCHAR(255) NOT NULL,
    instante_do_pagamento datetime(6) NOT NULL,
    status VARCHAR(255) NOT NULL,
    compra_id bigint NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_pagamento_compra_id FOREIGN KEY (compra_id) REFERENCES compra(id)
);
