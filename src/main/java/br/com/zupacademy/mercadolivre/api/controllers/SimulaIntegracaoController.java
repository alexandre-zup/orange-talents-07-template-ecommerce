package br.com.zupacademy.mercadolivre.api.controllers;

import br.com.zupacademy.mercadolivre.api.dto.request.simulaintegracao.NFRequest;
import br.com.zupacademy.mercadolivre.api.dto.request.simulaintegracao.RankingVendasRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SimulaIntegracaoController {

    @PostMapping("/notas-fiscais")
    public void geraNotaFiscal(@RequestBody @Valid NFRequest request) {
        System.out.println("Gerando NF");
        System.out.println("compra: " + request.getIdCompra());
        System.out.println("comprador: " + request.getIdComprador());
        System.out.println();
    }

    @PostMapping("/ranking-vendas")
    public void rankingVendas(@RequestBody @Valid RankingVendasRequest request) {
        System.out.println("Atualizando ranking de vendas");
        System.out.println("compra: " + request.getIdCompra());
        System.out.println("vendedor: " + request.getIdVendedor());
        System.out.println();
    }
}
