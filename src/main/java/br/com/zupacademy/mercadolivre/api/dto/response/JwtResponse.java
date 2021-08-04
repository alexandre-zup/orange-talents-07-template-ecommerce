package br.com.zupacademy.mercadolivre.api.dto.response;

public class JwtResponse {
    private String token;
    private String tipo;

    public JwtResponse(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }
}
