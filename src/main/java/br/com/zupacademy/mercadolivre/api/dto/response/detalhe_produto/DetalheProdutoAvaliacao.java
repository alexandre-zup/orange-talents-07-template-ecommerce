package br.com.zupacademy.mercadolivre.api.dto.response.detalhe_produto;

import br.com.zupacademy.mercadolivre.model.entities.AvalicaoProduto;

public class DetalheProdutoAvaliacao {
    private Integer nota;
    private String titulo;
    private String descricao;

    public DetalheProdutoAvaliacao(AvalicaoProduto avaliacao) {
        this.nota = avaliacao.getNota();
        this.titulo = avaliacao.getTitulo();
        this.descricao = avaliacao.getDescricao();
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
