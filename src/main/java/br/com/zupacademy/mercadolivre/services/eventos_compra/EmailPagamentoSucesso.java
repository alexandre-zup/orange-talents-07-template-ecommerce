package br.com.zupacademy.mercadolivre.services.eventos_compra;

import br.com.zupacademy.mercadolivre.model.entities.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailPagamentoSucesso implements EventoPagamentoSucesso {
    @Autowired
    private MailSender mailSender;

    @Override
    public void executa(Compra compra) {
        String texto = "Seu pagamento foi aprovado!"
                + "\nPedido número: " + compra.getId()
                + "\nProduto: " + compra.getProduto().getNome()
                + "\nValor do pedido: " + compra.totalDaCompra()
                + "\nMeio de pagamento: " + compra.getGatewayPagamento()
                + "\nContato do vendedor: " + compra.getProduto().getUsuario().getUsername()
                + "\n";

        SimpleMailMessage emial = new SimpleMailMessage();
        emial.setFrom("contato@mercadolivre.com.br");
        emial.setTo(compra.getComprador().getUsername());
        emial.setSubject("Pagamento aprovado");
        emial.setText(texto);
        mailSender.send(emial);
    }
}
