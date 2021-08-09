package br.com.zupacademy.mercadolivre.api.dto.request.simulaintegracao;

import javax.validation.constraints.NotNull;

public class RankingVendasRequest {
    @NotNull
    private Long idCompra;
    @NotNull
    private Long idVendedor;

    public RankingVendasRequest(Long idCompra, Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getIdVendedor() {
        return idVendedor;
    }
}
