package br.com.zupacademy.mercadolivre.api.dto.request.retorno_pagamento;

import br.com.zupacademy.mercadolivre.model.entities.Compra;
import br.com.zupacademy.mercadolivre.model.entities.Pagamento;

public interface RetornoPagamento {
    Pagamento toModel(Compra compra);
    Long getIdCompra();
}
