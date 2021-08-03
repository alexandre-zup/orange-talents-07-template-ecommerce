package br.com.zupacademy.mercadolivre.api.controllers;

import br.com.zupacademy.mercadolivre.api.dto.request.NovaCategoriaRequest;
import br.com.zupacademy.mercadolivre.model.entities.Categoria;
import br.com.zupacademy.mercadolivre.model.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository repository;

    @PostMapping
    @Transactional
    public void cadastra(@RequestBody @Valid NovaCategoriaRequest request) {
        Categoria categoria = request.toModel(repository);
        repository.save(categoria);
    }
}
