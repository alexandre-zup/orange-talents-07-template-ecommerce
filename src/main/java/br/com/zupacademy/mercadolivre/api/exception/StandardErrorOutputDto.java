package br.com.zupacademy.mercadolivre.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class StandardErrorOutputDto {
    private String message;
    private HttpStatus status;

    public StandardErrorOutputDto(RuntimeException ex, HttpStatus status) {
        this.message = ex.getMessage();
        this.status = status;
    }

    public StandardErrorOutputDto(ResponseStatusException ex) {
        this.message = ex.getReason();
        this.status = ex.getStatus();
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status.value();
    }
}
