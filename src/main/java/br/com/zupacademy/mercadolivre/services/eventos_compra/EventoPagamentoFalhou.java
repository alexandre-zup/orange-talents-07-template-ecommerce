package br.com.zupacademy.mercadolivre.services.eventos_compra;

import br.com.zupacademy.mercadolivre.model.entities.Compra;

public interface EventoPagamentoFalhou {
    void executa(Compra compra);
}
