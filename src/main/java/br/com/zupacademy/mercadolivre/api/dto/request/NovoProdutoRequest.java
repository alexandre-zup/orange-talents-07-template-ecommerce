package br.com.zupacademy.mercadolivre.api.dto.request;

import br.com.zupacademy.mercadolivre.api.validation.ExistsId;
import br.com.zupacademy.mercadolivre.model.entities.Categoria;
import br.com.zupacademy.mercadolivre.model.entities.Produto;
import br.com.zupacademy.mercadolivre.model.entities.Usuario;
import br.com.zupacademy.mercadolivre.model.repositories.CategoriaRepository;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Map;

public class NovoProdutoRequest {
    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @Min(0)
    private Integer quantidade;

    @NotNull
    @Size(min = 3)
    private Map<@NotBlank String, @NotBlank String> caracteristicas;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @NotNull
    @Positive
    @ExistsId(domainClass = Categoria.class, fieldName = "id", message = "{validation.existsId.produto.categoriaId}")
    private Long categoriaId;

    public NovoProdutoRequest(String nome, BigDecimal valor, Integer quantidade, Map<String, String> caracteristicas, String descricao, Long categoriaId) {
        Assert.isTrue(valor.doubleValue() >= 0.01, "Valor deve ser no mínimo 0.01");
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
    }

    public Produto toModel(CategoriaRepository categoriaRepository, Usuario usuario) {
        @NotNull Categoria categoria = categoriaRepository.findById(this.categoriaId).get();
        return new Produto(this.nome, this.valor, this.quantidade, this.caracteristicas, this.descricao, categoria, usuario);
    }
}
