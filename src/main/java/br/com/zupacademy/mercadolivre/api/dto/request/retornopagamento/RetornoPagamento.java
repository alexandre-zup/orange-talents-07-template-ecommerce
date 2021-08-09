package br.com.zupacademy.mercadolivre.api.dto.request.retornopagamento;

import br.com.zupacademy.mercadolivre.model.entities.Compra;
import br.com.zupacademy.mercadolivre.model.entities.Pagamento;

public interface RetornoPagamento {
    Pagamento toModel(Compra compra);
    Long getIdCompra();
}
