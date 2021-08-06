package br.com.zupacademy.mercadolivre.api.controllers;

import br.com.zupacademy.mercadolivre.api.dto.request.NovaAvaliacaoRequest;
import br.com.zupacademy.mercadolivre.api.dto.request.NovasImagensRequest;
import br.com.zupacademy.mercadolivre.api.dto.request.NovoProdutoRequest;
import br.com.zupacademy.mercadolivre.model.entities.AvalicaoProduto;
import br.com.zupacademy.mercadolivre.model.entities.Produto;
import br.com.zupacademy.mercadolivre.model.entities.Usuario;
import br.com.zupacademy.mercadolivre.model.repositories.CategoriaRepository;
import br.com.zupacademy.mercadolivre.model.repositories.ProdutoRepository;
import br.com.zupacademy.mercadolivre.uploaders.Uploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private Uploader uploader;

    @PostMapping
    @Transactional
    public void cadastra(@RequestBody @Valid NovoProdutoRequest request, @AuthenticationPrincipal Usuario usuarioLogado) {
        Produto produto = request.toModel(categoriaRepository, usuarioLogado);
        produtoRepository.save(produto);
    }

    @PostMapping("/{id}/imagens")
    @Transactional
    public ResponseEntity<Void> adicionaImagens(@Valid NovasImagensRequest request, @PathVariable Long id,
                                          @AuthenticationPrincipal Usuario usuarioLogado) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);

        if (optionalProduto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Produto produto = optionalProduto.get();

        if (!produto.pertenceA(usuarioLogado)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso restrito ao dono do produto");
        }

        List<String> links = uploader.envia(request.getImagens());

        produto.adicionaImagens(links);
        produtoRepository.save(produto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/avaliacoes")
    @Transactional
    public ResponseEntity<Void> adicionaAvaliacao(@RequestBody @Valid NovaAvaliacaoRequest request,
                                                  @PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);

        if(optionalProduto.isEmpty())
            return ResponseEntity.notFound().build();

        Produto produto = optionalProduto.get();
        AvalicaoProduto avalicaoProduto = request.toModel(produto, usuario);
        produto.adicionaAvaliacao(avalicaoProduto);
        produtoRepository.save(produto);
        return ResponseEntity.ok().build();
    }
}
