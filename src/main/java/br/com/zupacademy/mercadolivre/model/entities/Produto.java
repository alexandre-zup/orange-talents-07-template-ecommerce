package br.com.zupacademy.mercadolivre.model.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

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

    /**
     * Map que representará mapa de características usando pares String/String
     * onde a chave (String) será o nome da característica
     * e o valor (String) será a descrição da característica
     */
    @Size(min = 3)
    @NotNull
    @ElementCollection
    @CollectionTable(name = "caracteristica_produto",
            joinColumns = {@JoinColumn(name = "produto_id", referencedColumnName = "id")}
    )
    @MapKeyColumn(name = "nome_caracterisica")
    @Column(name = "descricao_caracteristica")
    private Map<@NotBlank String, @NotBlank String> caracteristicas;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

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

    public Produto(String nome, BigDecimal valor, Integer quantidade, Map<String, String> caracteristicas,
                   String descricao, Categoria categoria, Usuario usuario) {
        Assert.isTrue(valor.doubleValue() >= 0.01, "Valor deve ser no mínimo 0.01");

        this.usuario = usuario;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
    }
}
