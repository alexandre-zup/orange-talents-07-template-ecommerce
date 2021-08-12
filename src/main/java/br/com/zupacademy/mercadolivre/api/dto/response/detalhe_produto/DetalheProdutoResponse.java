package br.com.zupacademy.mercadolivre.api.dto.response.detalhe_produto;

import br.com.zupacademy.mercadolivre.model.entities.Produto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class DetalheProdutoResponse {
    private String nome;
    private String descricao;
    private BigDecimal valor;
    private Set<Map<String, String >> caracteristicas;
    private Set<String> imagens;
    private Set<DetalheProdutoPergunta> perguntas;
    private Set<DetalheProdutoAvaliacao> avaliacoes;
    private Double mediaNotas;
    private Integer quantidadeNotas;

    public DetalheProdutoResponse(Produto produto) {
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.valor = produto.getValor();
        this.caracteristicas = produto.mapeiaCaracteristicas(
                x -> Map.of("nome", x.getNome(), "descricao", x.getDescricao()));
        this.imagens = produto.mapeiaImagens(String::new);
        this.perguntas = produto.mapeiaPerguntas(DetalheProdutoPergunta::new);
        this.avaliacoes = produto.mapeiaAvaliacoes(DetalheProdutoAvaliacao::new);
        this.mediaNotas = this.avaliacoes.stream().mapToDouble(DetalheProdutoAvaliacao::getNota).average().orElse(0);
        this.quantidadeNotas = this.avaliacoes.size();
    }

    public Integer getQuantidadeNotas() {
        return quantidadeNotas;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Set<Map<String, String >> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getImagens() {
        return imagens;
    }

    public Set<DetalheProdutoPergunta> getPerguntas() {
        return perguntas;
    }

    public Set<DetalheProdutoAvaliacao> getAvaliacoes() {
        return avaliacoes;
    }

}
