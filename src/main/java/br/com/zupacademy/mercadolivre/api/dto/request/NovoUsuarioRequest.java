package br.com.zupacademy.mercadolivre.api.dto.request;

import br.com.zupacademy.mercadolivre.api.validation.UniqueValue;
import br.com.zupacademy.mercadolivre.model.entities.SenhaLimpa;
import br.com.zupacademy.mercadolivre.model.entities.Usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NovoUsuarioRequest {
    @NotBlank
    @Email
    @UniqueValue(fieldName = "login", domainClass = Usuario.class, message = "{validation.uniquevalue.usuario.login}")
    private final String login;

    @NotBlank
    @Size(min = 6)
    private final String senha;

    public NovoUsuarioRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toModel() {
        return new Usuario(this.login, new SenhaLimpa(this.senha));
    }
}
