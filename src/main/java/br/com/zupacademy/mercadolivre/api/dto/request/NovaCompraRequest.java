package br.com.zupacademy.mercadolivre.api.dto.request;

import br.com.zupacademy.mercadolivre.api.validation.ExistsId;
import br.com.zupacademy.mercadolivre.model.entities.Compra;
import br.com.zupacademy.mercadolivre.model.entities.Produto;
import br.com.zupacademy.mercadolivre.model.entities.Usuario;
import br.com.zupacademy.mercadolivre.model.entities.enums.GatewayPagamento;
import org.springframework.util.Assert;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NovaCompraRequest {
    @NotNull
    private GatewayPagamento gatewayPagamento;

    @ExistsId(domainClass = Produto.class, fieldName = "id")
    private Long produtoId;

    @NotNull
    @Positive
    private Integer quantidade;

    public NovaCompraRequest(GatewayPagamento gatewayPagamento, Long produtoId, Integer quantidade) {
        this.gatewayPagamento = gatewayPagamento;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    public Compra toModel(@NotNull @Valid Usuario comprador, @NotNull @Valid Produto produto) {
        Assert.notNull(comprador, "Usuário deve ser válido");
        Assert.notNull(produto, "Produto deve ser válido");
        return new Compra(this.quantidade, this.gatewayPagamento, comprador, produto);
    }


    public Long getProdutoId() {
        return produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }
}
