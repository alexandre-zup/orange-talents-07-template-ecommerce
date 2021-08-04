package br.com.zupacademy.mercadolivre.model.entities.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SenhaLimpa {
    private final String senhaLimpa;

    public SenhaLimpa(@NotBlank @Size(min = 6) String senhaLimpa) {
        Assert.hasLength(senhaLimpa, "A senha nao pode estar em branco");
        Assert.isTrue(senhaLimpa.length() >= 6, "A senha deve ter no mínimo 6 caracteres");

        this.senhaLimpa = senhaLimpa;
    }

    public String getSenhaEncodada() {
        return new BCryptPasswordEncoder().encode(this.senhaLimpa);
    }

    public int getTamanhoSenha() {
        return this.senhaLimpa.length();
    }
}