package br.com.zupacademy.mercadolivre.api.dto.request;

public class CredenciaisRequest {
    private String login;
    private String senha;

    public CredenciaisRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
