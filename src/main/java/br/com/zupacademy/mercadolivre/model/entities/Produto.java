package br.com.zupacademy.mercadolivre.model.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<AvalicaoProduto> avalicaoes = new ArrayList<>();

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private List<PerguntaProduto> perguntas = new ArrayList<>();

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

    public String obterContatoDono() {
        return this.usuario.getUsername();
    }

    public boolean reduzQuantidade(@Positive int quantidadeParaReduzir) {
        Assert.isTrue(quantidadeParaReduzir > 0, "Quantidade deve ser maior que 0");

        if(this.quantidade < quantidadeParaReduzir)
            return false;

        this.quantidade -= quantidadeParaReduzir;
        return true;
    }

    public void adicionaImagens(List<String> urlDasImagens) {
        imagens.addAll(urlDasImagens);
    }

    public void adicionaAvaliacao(AvalicaoProduto avalicaoProduto) {
        this.avalicaoes.add(avalicaoProduto);
    }

    public void adicionaPergunta(PerguntaProduto pergunta) {
        this.perguntas.add(pergunta);
    }

    public boolean pertenceA(Usuario possivelDono) {
        return this.usuario.equals(possivelDono);
    }

    public Usuario dono() {
        return this.usuario;
    }

    public <T> Set<T> mapeiaCaracteristicas(Function<CaracteristicaProduto, T> funcaoMapeadora) {
        return this.caracteristicas.stream().map(funcaoMapeadora).collect(Collectors.toSet());
    }

    public <T> Set<T> mapeiaImagens(Function<String, T> funcaoMapeadora) {
        return this.imagens.stream().map(funcaoMapeadora).collect(Collectors.toSet());
    }

    public <T> Set<T> mapeiaAvaliacoes(Function<AvalicaoProduto, T> funcaoMapeadora) {
        return this.avalicaoes.stream().map(funcaoMapeadora).collect(Collectors.toSet());
    }

    public <T> Set<T> mapeiaPerguntas(Function<PerguntaProduto, T> funcaoMapeadora) {
        return this.perguntas.stream().map(funcaoMapeadora).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
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
        return new StringJoiner(", ", Produto.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("nome='" + nome + "'")
                .add("valor=" + valor)
                .add("quantidade=" + quantidade)
                .add("caracteristicas=" + caracteristicas)
                .add("descricao='" + descricao + "'")
                .add("imagens=" + imagens)
                .add("categoria=" + categoria)
                .add("usuario=" + usuario)
                .add("criadoEm=" + criadoEm)
                .toString();
    }
}
