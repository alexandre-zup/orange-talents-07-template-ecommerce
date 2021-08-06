package br.com.zupacademy.mercadolivre.model.entities;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class AvalicaoProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(1)
    @Max(5)
    @NotNull
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Length(max = 500)
    private String descricao;

    @NotNull
    @ManyToOne
    private Produto produto;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    /**
     * @deprecated exclusivo para usao dos frameworks
     */
    @Deprecated
    public AvalicaoProduto() {
    }

    public AvalicaoProduto(Integer nota, String titulo, String descricao, Produto produto, Usuario usuario) {
        Assert.isTrue(nota >= 1 && nota <= 5, "Nota deve ser entre 1 e 5");
        Assert.hasText(titulo, "Título é obrigatório");
        Assert.hasText(descricao, "Descrição é obrigatória");
        Assert.isTrue(descricao.length() <= 500, "Descrição deve ter no máximo 500 caracteres");
        Assert.notNull(produto,"Produto relacionado é obrigatório");
        Assert.notNull(usuario, "Usuário da opinião é obrigatório");

        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvalicaoProduto that = (AvalicaoProduto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AvalicaoProduto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("nota=" + nota)
                .add("titulo='" + titulo + "'")
                .add("descricao='" + descricao + "'")
                .add("produto=" + produto)
                .add("usuario=" + usuario)
                .toString();
    }
}
