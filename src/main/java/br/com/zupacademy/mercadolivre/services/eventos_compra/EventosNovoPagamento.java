package br.com.zupacademy.mercadolivre.services.eventos_compra;

import br.com.zupacademy.mercadolivre.model.entities.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventosNovoPagamento {
    @Autowired
    private Set<EventoPagamentoSucesso> eventosPagamentoComSucesso;

    @Autowired
    private Set<EventoPagamentoFalhou> eventosPagamentoFalhou;

    public void processa(Compra compra) {
        if (compra.processadaComSucesso()) {
            eventosPagamentoComSucesso.forEach(evento -> evento.executa(compra));
        } else {
            eventosPagamentoFalhou.forEach(evento -> evento.executa(compra));
        }
    }
}
