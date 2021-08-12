package br.com.zupacademy.mercadolivre.services.eventos_compra;

import br.com.zupacademy.mercadolivre.model.entities.Compra;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RankingVendas implements EventoPagamentoSucesso {
    @Override
    public void executa(Compra compra) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(),
                "idVendedor", compra.getProduto().getUsuario().getId());

        restTemplate.postForEntity("http://localhost:8080/ranking-vendas",
                request, String.class);
    }
}
