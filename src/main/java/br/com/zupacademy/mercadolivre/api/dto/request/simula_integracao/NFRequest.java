package br.com.zupacademy.mercadolivre.api.dto.request.simula_integracao;

import javax.validation.constraints.NotNull;

public class NFRequest {
    @NotNull
    private Long idCompra;

    @NotNull
    private Long idComprador;

    public NFRequest(Long idCompra, Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdComprador() {
        return idComprador;
    }
}
