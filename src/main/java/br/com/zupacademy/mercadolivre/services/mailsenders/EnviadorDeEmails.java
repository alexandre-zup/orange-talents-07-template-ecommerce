package br.com.zupacademy.mercadolivre.services.mailsenders;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class EnviadorDeEmails implements MailSender {

    @Override
    public void send(SimpleMailMessage simpleMessage) throws MailException {
        System.out.println("Enviando email\n===================");
        System.out.println("De: " + simpleMessage.getFrom());
        System.out.println("Para: " + Arrays.toString(simpleMessage.getTo()));
        System.out.println("Assunto: " + simpleMessage.getSubject());
        System.out.println("Conteúdo: " + simpleMessage.getText());
        System.out.println();
    }

    @Override
    public void send(SimpleMailMessage... simpleMessages) throws MailException {
        for(SimpleMailMessage message : simpleMessages) {
            System.out.println("Enviando email\n");
            System.out.println("Remetente: " + message.getFrom());
            System.out.println("Destinatários: " + Arrays.toString(message.getTo()));
            System.out.println("Assunto: " + message.getSubject());
            System.out.println("Conteúdo: " + message.getText());
            System.out.println();
        }
    }
}
