package br.com.zupacademy.mercadolivre.model.entities.enums;

import org.springframework.security.core.GrantedAuthority;


public enum NomePerfil implements GrantedAuthority {
    USUARIO;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
