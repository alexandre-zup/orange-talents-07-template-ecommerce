package br.com.zupacademy.mercadolivre.api.controllers;

import br.com.zupacademy.mercadolivre.api.dto.request.NovaAvaliacaoRequest;
import br.com.zupacademy.mercadolivre.api.dto.response.detalhe_produto.DetalheProdutoResponse;
import br.com.zupacademy.mercadolivre.model.entities.AvalicaoProduto;
import br.com.zupacademy.mercadolivre.model.entities.Produto;
import br.com.zupacademy.mercadolivre.model.entities.Usuario;
import br.com.zupacademy.mercadolivre.model.repositories.ProdutoRepository;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private Tracer tracer;

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

    @GetMapping("/{id}")
    public ResponseEntity<DetalheProdutoResponse> detalha(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
        tracer.activeSpan().setTag("produto.id", id);
        tracer.activeSpan().setTag("usuario.email", usuario.getUsername());
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if(produtoOptional.isEmpty())
            return ResponseEntity.notFound().build();

        DetalheProdutoResponse response = new DetalheProdutoResponse(produtoOptional.get());
        return ResponseEntity.ok().body(response);
    }
}
