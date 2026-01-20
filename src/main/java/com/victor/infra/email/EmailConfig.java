package com.victor.infra.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSender javaMailSender(
            @Value("${spring.mail.host:}") String host,
            @Value("${spring.mail.port:25}") int port,
            @Value("${spring.mail.username:}") String username,
            @Value("${spring.mail.password:}") String password,
            @Value("${spring.mail.properties.mail.smtp.auth:false}") boolean smtpAuth,
            @Value("${spring.mail.properties.mail.smtp.starttls.enable:false}") boolean starttls
    ) {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        if (!host.isEmpty()) {
            sender.setHost(host);
        }
        sender.setPort(port);
        if (!username.isEmpty()) {
            sender.setUsername(username);
            sender.setPassword(password);
        }

        Properties props = sender.getJavaMailProperties();
        props.put("mail.smtp.auth", String.valueOf(smtpAuth));
        props.put("mail.smtp.starttls.enable", String.valueOf(starttls));

        return sender;
    }
}

