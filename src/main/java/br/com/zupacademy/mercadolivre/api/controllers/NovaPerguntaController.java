package br.com.zupacademy.mercadolivre.api.controllers;

import br.com.zupacademy.mercadolivre.api.dto.request.NovaPerguntaRequest;
import br.com.zupacademy.mercadolivre.model.entities.PerguntaProduto;
import br.com.zupacademy.mercadolivre.model.entities.Produto;
import br.com.zupacademy.mercadolivre.model.entities.Usuario;
import br.com.zupacademy.mercadolivre.model.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produtos/{id}/perguntas")
public class NovaPerguntaController {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private MailSender mailSender;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> adicionaPergunta(@RequestBody @Valid NovaPerguntaRequest request, @PathVariable Long id,
                                                 @AuthenticationPrincipal Usuario usuarioLogado) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);

        if(produtoOptional.isEmpty())
            return ResponseEntity.notFound().build();

        Produto produto = produtoOptional.get();
        PerguntaProduto pergunta = request.toModel(produto, usuarioLogado);
        produto.adicionaPergunta(pergunta);
        produtoRepository.save(produto);

        //enviando o email
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(produto.dono().getUsername());
        mail.setFrom("contao@mercadolivre.com.br");
        mail.setSubject("Responda a pergunta do usuário: " + usuarioLogado.getUsername());
        mail.setText("Pergunta -> " + request.getTitulo());
        mailSender.send(mail);

        return ResponseEntity.ok().build();
    }
}
