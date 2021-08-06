package br.com.zupacademy.mercadolivre.model.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @Min(0)
    private Integer quantidade;

    @Size(min = 3)
    @NotNull
    @ElementCollection
    @CollectionTable(name = "caracteristica_produto",
            joinColumns = {@JoinColumn(name = "produto_id", referencedColumnName = "id")}
    )
    private Set<CaracteristicaProduto> caracteristicas;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @ElementCollection
    @CollectionTable(name = "imagem_produto",
            joinColumns = {@JoinColumn(name = "produto_id", referencedColumnName = "id")}
    )
    @Column(name = "url", nullable = false)
    private Set<String> imagens = new HashSet<>();

    @NotNull
    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    private Usuario usuario;

    @CreationTimestamp
    private LocalDateTime criadoEm;

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, BigDecimal valor, Integer quantidade, Set<CaracteristicaProduto> caracteristicas,
                   String descricao, Categoria categoria, Usuario usuario) {
        Assert.isTrue(valor.doubleValue() >= 0.01, "Valor deve ser no mínimo 0.01");

        this.usuario = usuario;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;

        this.descricao = descricao;
        this.categoria = categoria;
        this.caracteristicas = caracteristicas;
    }

    public void adicionaImagens(List<String> urlDasImagens) {
        imagens.addAll(urlDasImagens);
    }

    public boolean pertenceA(Usuario possivelDono) {
        return this.usuario.equals(possivelDono);
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", caracteristicas=" + caracteristicas +
                ", descricao='" + descricao + '\'' +
                ", imagens=" + imagens +
                ", categoria=" + categoria +
                ", usuario=" + usuario +
                ", criadoEm=" + criadoEm +
                '}';
    }
}
