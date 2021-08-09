package br.com.zupacademy.mercadolivre.model.entities;

import br.com.zupacademy.mercadolivre.model.entities.enums.StatusPagamento;
import br.com.zupacademy.mercadolivre.model.entities.enums.GatewayPagamento;
import io.jsonwebtoken.lang.Assert;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String idPagamentoNoGateway;

    @CreationTimestamp
    private LocalDateTime instanteDoPagamento;

    @NotNull
    @Valid
    @ManyToOne
    private Compra compra;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GatewayPagamento gatewayPagamento;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    @Deprecated
    public Pagamento() {
    }

    public Pagamento(String idPagamentoNoGateway, Compra compra, GatewayPagamento gateway, StatusPagamento status) {
        Assert.notNull(idPagamentoNoGateway, "Id do pagamento não pode ser nulo");
        Assert.hasText(idPagamentoNoGateway, "Id do pagamento não pode ser em branco");
        Assert.notNull(compra, "Compra não pode ser nula");
        Assert.notNull(gateway, "Gateway de pagamento não pode ser nulo");
        Assert.notNull(status, "Status não pode ser nulo");

        this.idPagamentoNoGateway = idPagamentoNoGateway;
        this.compra = compra;
        this.gatewayPagamento = gateway;
        this.status = status;
    }

    public boolean concluidoComSucesso() {
        return status.equals(StatusPagamento.SUCESSO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Pagamento.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("idPagamentoNoGateway='" + idPagamentoNoGateway + "'")
                .add("instanteDoPagamento=" + instanteDoPagamento)
                .add("gatewayPagamento=" + gatewayPagamento)
                .toString();
    }
}
