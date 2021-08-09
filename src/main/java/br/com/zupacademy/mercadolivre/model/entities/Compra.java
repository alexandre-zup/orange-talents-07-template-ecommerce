package br.com.zupacademy.mercadolivre.model.entities;

import br.com.zupacademy.mercadolivre.model.entities.enums.GatewayPagamento;
import br.com.zupacademy.mercadolivre.model.entities.enums.StatusCompra;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private Set<Pagamento> pagamentos = new HashSet<>();

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

    public String obterUrlDePagamento(String urlBase) {
        return this.gatewayPagamento.obterUrlPagamento(this.id, urlBase);
    }

    public void adicionaPagamento(Pagamento pagamento) {
        if(!processadaComSucesso()) {
            this.pagamentos.add(pagamento);

            if(pagamento.concluidoComSucesso()) {
                this.status = StatusCompra.PAGA;
            }
        }
    }

    public boolean processadaComSucesso() {
        Set<Pagamento> pagamentosComSucesso = this.pagamentos.stream().filter(Pagamento::concluidoComSucesso)
                .collect(Collectors.toSet());

        Assert.state(pagamentosComSucesso.size() <= 1,
                "Mais de um pagamento com sucesso para a compra: " + this.id);

        return !pagamentosComSucesso.isEmpty();
    }

    public BigDecimal totalDaCompra() {
        return valorNoMomentoDaCompra.multiply(BigDecimal.valueOf(quantidadeComprada));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compra compra = (Compra) o;
        return Objects.equals(id, compra.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Compra.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("status=" + status)
                .add("quantidadeComprada=" + quantidadeComprada)
                .add("valorNoMomentoDaCompra=" + valorNoMomentoDaCompra)
                .add("gatewayPagamento=" + gatewayPagamento)
                .add("pagamentos=" + pagamentos)
                .toString();
    }

    public Long getId() {
        return id;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public Produto getProduto() {
        return produto;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }

    public BigDecimal getValorNoMomentoDaCompra() {
        return valorNoMomentoDaCompra;
    }

    public Integer getQuantidadeComprada() {
        return quantidadeComprada;
    }
}
