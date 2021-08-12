package br.com.zupacademy.mercadolivre.api.controllers;

import br.com.zupacademy.mercadolivre.api.dto.request.retorno_pagamento.RetornoPagamento;
import br.com.zupacademy.mercadolivre.api.dto.request.retorno_pagamento.RetornoPagseguroRequest;
import br.com.zupacademy.mercadolivre.api.dto.request.retorno_pagamento.RetornoPayPalRequest;
import br.com.zupacademy.mercadolivre.api.exception.MinhaException;
import br.com.zupacademy.mercadolivre.model.entities.Compra;
import br.com.zupacademy.mercadolivre.model.entities.Pagamento;
import br.com.zupacademy.mercadolivre.model.repositories.CompraRepository;
import br.com.zupacademy.mercadolivre.services.eventos_compra.EventosNovoPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class RetornoPagamentoController {
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private EventosNovoPagamento eventosNovoPagamento;

    @PostMapping("/retorno-paypal")
    @Transactional
    public void retornoPaypal(@RequestBody @Valid RetornoPayPalRequest request) {
        processaPagamento(request);
    }

    @PostMapping("/retorno-pagseguro")
    @Transactional
    public void retornoPagseguro(@RequestBody @Valid RetornoPagseguroRequest request) {
        processaPagamento(request);
    }

    private void processaPagamento(RetornoPagamento retorno) {
        Compra compra = compraRepository.findById(retorno.getIdCompra()).get();

        if(compra.processadaComSucesso())
            throw new MinhaException(HttpStatus.BAD_REQUEST, "Pagamento já processado");

        Pagamento pagamento = retorno.toModel(compra);
        compra.adicionaPagamento(pagamento);
        compraRepository.save(compra);
        eventosNovoPagamento.processa(compra);
    }
}
