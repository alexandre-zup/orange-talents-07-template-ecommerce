package br.com.zupacademy.mercadolivre.services.eventos_compra;

import br.com.zupacademy.mercadolivre.model.entities.Compra;

public interface EventoPagamentoSucesso {
    public void executa(Compra compra);
}
