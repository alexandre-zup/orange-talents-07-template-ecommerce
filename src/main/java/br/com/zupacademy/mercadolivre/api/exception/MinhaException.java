package br.com.zupacademy.mercadolivre.api.exception;

import org.springframework.http.HttpStatus;

public class MinhaException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    public MinhaException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
