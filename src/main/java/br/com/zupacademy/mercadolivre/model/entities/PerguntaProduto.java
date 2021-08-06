package br.com.zupacademy.mercadolivre.model.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class PerguntaProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @CreationTimestamp
    private LocalDateTime criadaEm;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @NotNull
    @ManyToOne
    private Produto produto;

    /**
     * @deprecated exclusivo para frameworks
     */
    @Deprecated
    public PerguntaProduto() {
    }

    public PerguntaProduto(@NotBlank String titulo, @NotNull @Valid Usuario usuario, @NotNull @Valid Produto produto) {
        Assert.hasText(titulo, "Título é obrigatório");
        Assert.notNull(usuario, "Usuário não pode ser nulo");
        Assert.notNull(produto, "Produto não pode ser nulo");

        this.titulo = titulo;
        this.usuario = usuario;
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerguntaProduto that = (PerguntaProduto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PerguntaProduto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("titulo='" + titulo + "'")
                .add("criadaEm=" + criadaEm)
                .add("usuario=" + usuario)
                .add("produto=" + produto)
                .toString();
    }
}
