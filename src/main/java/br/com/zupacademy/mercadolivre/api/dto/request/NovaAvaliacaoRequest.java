package br.com.zupacademy.mercadolivre.api.dto.request;

import br.com.zupacademy.mercadolivre.model.entities.AvalicaoProduto;
import br.com.zupacademy.mercadolivre.model.entities.Produto;
import br.com.zupacademy.mercadolivre.model.entities.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

public class NovaAvaliacaoRequest {
    @Min(1)
    @Max(5)
    @NotNull
    private Integer nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Length(max = 500)
    private String descricao;

    public NovaAvaliacaoRequest(Integer nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public AvalicaoProduto toModel(Produto produto, Usuario usuario) {
        return new AvalicaoProduto(this.nota, this.titulo, this.descricao, produto, usuario);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", NovaAvaliacaoRequest.class.getSimpleName() + "[", "]")
                .add("nota=" + nota)
                .add("titulo='" + titulo + "'")
                .add("descricao='" + descricao + "'")
                .toString();
    }
}
