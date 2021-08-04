package br.com.zupacademy.mercadolivre.api.dto.request;

import br.com.zupacademy.mercadolivre.api.validation.ExistsIdIfNotNull;
import br.com.zupacademy.mercadolivre.api.validation.UniqueValue;
import br.com.zupacademy.mercadolivre.model.entities.Categoria;
import br.com.zupacademy.mercadolivre.model.repositories.CategoriaRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class NovaCategoriaRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "{validation.uniquevalue.categoria.nome}")
    private String nome;

    @Positive
    @ExistsIdIfNotNull(domainClass = Categoria.class, fieldName = "id",
            message = "{validation.existsIdIfNotNull.categoria.maeId}")
    private Long categoriaMaeId;

    public NovaCategoriaRequest(String nome, Long categoriaMaeId) {
        this.nome = nome;
        this.categoriaMaeId = categoriaMaeId;
    }

    public Categoria toModel(CategoriaRepository repository) {
        Categoria mae = null;

        if(categoriaMaeId != null)
            mae = repository.findById(categoriaMaeId).orElse(null);

        Categoria categoria = new Categoria(this.nome);
        categoria.setCategoriaMae(mae);
        return categoria;
    }

    public String getNome() {
        return nome;
    }

    public Long getCategoriaMaeId() {
        return categoriaMaeId;
    }
}
