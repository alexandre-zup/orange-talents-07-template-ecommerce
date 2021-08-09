package br.com.zupacademy.mercadolivre.services.eventoscompra;

import br.com.zupacademy.mercadolivre.model.entities.Compra;

public interface EventoPagamentoFalhou {
    void executa(Compra compra);
}
