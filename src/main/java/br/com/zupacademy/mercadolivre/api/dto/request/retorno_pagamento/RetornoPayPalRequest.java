package br.com.zupacademy.mercadolivre.api.dto.request.retorno_pagamento;

import br.com.zupacademy.mercadolivre.api.validation.ExistsId;
import br.com.zupacademy.mercadolivre.model.entities.Compra;
import br.com.zupacademy.mercadolivre.model.entities.Pagamento;
import br.com.zupacademy.mercadolivre.model.entities.enums.GatewayPagamento;
import br.com.zupacademy.mercadolivre.model.entities.enums.StatusPagamento;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.StringJoiner;

public class RetornoPayPalRequest implements RetornoPagamento {
    @NotNull
    @Positive
    @ExistsId(domainClass = Compra.class, fieldName = "id")
    private Long idCompra;

    @NotBlank
    private String idPagamento;

    @NotNull
    private GatewayPagamento gateway;

    @NotNull
    private StatusPagamento status;


    public RetornoPayPalRequest(Long idCompra, String idPagamento, int status) {
        this.idCompra = idCompra;
        this.idPagamento = idPagamento;
        this.gateway = GatewayPagamento.PAYPAL;
        this.status = status == 1 ? StatusPagamento.SUCESSO : StatusPagamento.ERRO;
    }

    @Override
    public Pagamento toModel(Compra compra) {
        return new Pagamento(this.idPagamento, compra, this.gateway, status);
    }

    @Override
    public Long getIdCompra() {
        return idCompra;
    }

    public String getIdPagamento() {
        return idPagamento;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RetornoPayPalRequest.class.getSimpleName() + "[", "]")
                .add("idCompra=" + idCompra)
                .add("idPagamento='" + idPagamento + "'")
                .add("status=" + status)
                .add("gateway=" + gateway)
                .toString();
    }
}
