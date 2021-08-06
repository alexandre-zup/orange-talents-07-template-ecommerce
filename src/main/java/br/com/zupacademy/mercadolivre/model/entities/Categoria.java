package br.com.zupacademy.mercadolivre.model.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @ManyToOne
    private Categoria categoriaMae;

    @Deprecated
    public Categoria() {
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public void setCategoriaMae(Categoria categoriaMae) {
        this.categoriaMae = categoriaMae;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(id, categoria.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Categoria.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("nome='" + nome + "'")
                .add("categoriaMae=" + categoriaMae)
                .toString();
    }
}
