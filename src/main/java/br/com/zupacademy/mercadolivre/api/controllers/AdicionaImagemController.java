package br.com.zupacademy.mercadolivre.api.controllers;

import br.com.zupacademy.mercadolivre.api.dto.request.NovasImagensRequest;
import br.com.zupacademy.mercadolivre.model.entities.Produto;
import br.com.zupacademy.mercadolivre.model.entities.Usuario;
import br.com.zupacademy.mercadolivre.model.repositories.ProdutoRepository;
import br.com.zupacademy.mercadolivre.services.uploaders.Uploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos/{id}/imagens")
public class AdicionaImagemController {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private Uploader uploader;

    @PostMapping
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
}
