package br.com.zupacademy.mercadolivre.model.entities;

import br.com.zupacademy.mercadolivre.model.entities.enums.GatewayPagamento;
import br.com.zupacademy.mercadolivre.model.entities.enums.StatusCompra;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusCompra status;

    @NotNull
    @Positive
    private Integer quantidadeComprada;

    @NotNull
    @Positive
    private BigDecimal valorNoMomentoDaCompra;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GatewayPagamento gatewayPagamento;

    @NotNull
    @ManyToOne
    private Usuario comprador;

    @NotNull
    @ManyToOne
    private Produto produto;

    /**
     * @deprecated uso exclusivo de frameworks
     */
    @Deprecated
    public Compra() {
    }

    public Compra(@NotNull @Positive Integer quantidadeComprada, @NotNull GatewayPagamento gatewayPagamento,
                  @NotNull @Valid Usuario comprador, @NotNull @Valid Produto produto) {
        Assert.notNull(gatewayPagamento, "Gateway de pagamento inválido");
        Assert.notNull(comprador, "Comprador não pode ser nulo");
        Assert.notNull(produto, "Produto não pode ser nulo");
        Assert.notNull(quantidadeComprada, "Quantidade não pode ser nula");
        Assert.isTrue(quantidadeComprada > 0, "Quantidade comprada deve ser positiva");

        this.status = StatusCompra.INICIADA;
        this.quantidadeComprada = quantidadeComprada;
        this.gatewayPagamento = gatewayPagamento;
        this.comprador = comprador;
        this.produto = produto;
        this.valorNoMomentoDaCompra = this.produto.getValor();
    }

    public Long getId() {
        return id;
    }
}
