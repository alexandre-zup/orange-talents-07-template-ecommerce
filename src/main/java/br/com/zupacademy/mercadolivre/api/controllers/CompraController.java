package br.com.zupacademy.mercadolivre.api.controllers;

import br.com.zupacademy.mercadolivre.api.dto.request.NovaCompraRequest;
import br.com.zupacademy.mercadolivre.api.exception.MinhaException;
import br.com.zupacademy.mercadolivre.model.entities.Compra;
import br.com.zupacademy.mercadolivre.model.entities.Produto;
import br.com.zupacademy.mercadolivre.model.entities.Usuario;
import br.com.zupacademy.mercadolivre.model.repositories.CompraRepository;
import br.com.zupacademy.mercadolivre.model.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private MailSender mailSender;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CompraRepository compraRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> efetuaCompra(@RequestBody @Valid NovaCompraRequest request,
                                             @AuthenticationPrincipal Usuario comprador,
                                             UriComponentsBuilder uriComponentsBuilder) throws BindException {
        Produto produto = produtoRepository.findById(request.getProdutoId()).get();

        if(!produto.reduzQuantidade(request.getQuantidade())) {
            throw new MinhaException(HttpStatus.BAD_REQUEST, "Não há estoque suficiente do produto");
        }

        Compra compra = request.toModel(comprador, produto);
        compraRepository.save(compra);

        //enviando email
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("contato@mercadolivre.com.br");
        email.setTo(produto.obterContatoDono());
        email.setSubject("Venda iniciada");
        email.setText("Iniciada a venda do produto " + produto.getNome() + " para o usuário " + comprador.getUsername());
        mailSender.send(email);

        String urlBase = uriComponentsBuilder.toUriString();

        HttpHeaders location = new HttpHeaders();
        location.add("Location", request.getGatewayPagamento().obterUrlPagamento(compra.getId(), urlBase));
        return new ResponseEntity<Void>(location, HttpStatus.FOUND);
    }
}
