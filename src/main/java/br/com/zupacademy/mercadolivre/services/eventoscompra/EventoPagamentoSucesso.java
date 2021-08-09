package br.com.zupacademy.mercadolivre.services.eventoscompra;

import br.com.zupacademy.mercadolivre.model.entities.Compra;

public interface EventoPagamentoSucesso {
    public void executa(Compra compra);
}
