package br.com.zupacademy.mercadolivre.model.entities;

import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.StringJoiner;

@Embeddable
public class CaracteristicaProduto {
    private String nome;
    private String descricao;

    @Deprecated
    public CaracteristicaProduto() {
    }

    public CaracteristicaProduto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaracteristicaProduto that = (CaracteristicaProduto) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CaracteristicaProduto.class.getSimpleName() + "[", "]")
                .add("nome='" + nome + "'")
                .add("descricao='" + descricao + "'")
                .toString();
    }
}
