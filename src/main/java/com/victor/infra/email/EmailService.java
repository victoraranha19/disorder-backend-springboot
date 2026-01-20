package com.victor.infra.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Value("${spring.mail.username:email}")
    private String remetente;

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarNovaSenha(String destinatario, String novaSenha) throws MailException {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("DisOrder <" + remetente + ">");
            message.setTo(destinatario);
            message.setSubject("Recuperação de Senha");
            message.setText("Sua nova senha é: " + novaSenha);
            mailSender.send(message);
        } catch (MailException e) {
            throw new MailSendException("Erro ao enviar email.");
        }
    }
}
