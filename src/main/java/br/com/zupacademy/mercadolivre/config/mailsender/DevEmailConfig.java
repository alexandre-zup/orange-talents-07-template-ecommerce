package br.com.zupacademy.mercadolivre.config.mailsender;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;

@Configuration
@Profile("dev")
public class DevEmailConfig {

    @Bean
    public MailSender getMailSender() {
        return new FakeEnviadorEmails();
    }
}
