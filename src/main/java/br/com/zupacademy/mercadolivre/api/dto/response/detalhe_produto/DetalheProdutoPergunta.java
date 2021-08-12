package br.com.zupacademy.mercadolivre.api.dto.response.detalhe_produto;

import br.com.zupacademy.mercadolivre.model.entities.PerguntaProduto;

import java.time.LocalDateTime;

public class DetalheProdutoPergunta {
    private String titulo;
    private LocalDateTime dataDeCriacao;

    public DetalheProdutoPergunta(PerguntaProduto pergunta) {
        this.titulo = pergunta.getTitulo();
        this.dataDeCriacao = pergunta.getCriadaEm();
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
