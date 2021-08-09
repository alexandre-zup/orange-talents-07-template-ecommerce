package br.com.zupacademy.mercadolivre.model.entities.enums;

public enum GatewayPagamento {
    PAYPAL {
        @Override
        public String obterUrlPagamento(Long compraId, String baseUrl) {
            return "https://paypal.com?buyerId=" + compraId +
                    "&redirectUrl=" + baseUrl + "/retorno-paypal";
        }
    }
    , PAGSEGURO {
        @Override
        public String obterUrlPagamento(Long compraId, String urlBase) {
            return "https://pagseguro.com?returnId=" + compraId +
                    "&redirectUrl=" + urlBase + "/retorno-pagseguro";
        }
    };

    public abstract String obterUrlPagamento(Long compraId, String baseUrl);
}
