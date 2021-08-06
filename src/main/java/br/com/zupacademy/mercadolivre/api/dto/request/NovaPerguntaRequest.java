package br.com.zupacademy.mercadolivre.api.dto.request;

import br.com.zupacademy.mercadolivre.model.entities.PerguntaProduto;
import br.com.zupacademy.mercadolivre.model.entities.Produto;
import br.com.zupacademy.mercadolivre.model.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.Assert;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

public class NovaPerguntaRequest {
    @NotBlank
    private String titulo;

    public NovaPerguntaRequest(@JsonProperty("titulo") @NotBlank String titulo) {
        this.titulo = titulo;
    }

    public PerguntaProduto toModel(@NotNull @Valid Produto produto, @NotNull @Valid Usuario usuario) {
        Assert.notNull(produto, "Produto não pode ser nulo");
        Assert.notNull(usuario, "Usuário não pode ser nulo");
        return new PerguntaProduto(this.titulo, usuario, produto);
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", NovaPerguntaRequest.class.getSimpleName() + "[", "]")
                .add("titulo='" + titulo + "'")
                .toString();
    }
}
