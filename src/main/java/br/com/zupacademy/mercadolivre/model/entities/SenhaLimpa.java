package br.com.zupacademy.mercadolivre.model.entities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaLimpa {
    private final String senhaLimpa;

    public SenhaLimpa(String senhaLimpa) {
        this.senhaLimpa = senhaLimpa;
    }

    public String getSenhaEncodada() {
        return new BCryptPasswordEncoder().encode(this.senhaLimpa);
    }

    public int getTamanhoSenha() {
        return this.senhaLimpa.length();
    }
}