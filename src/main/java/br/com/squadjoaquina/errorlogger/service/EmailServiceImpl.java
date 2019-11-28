package br.com.squadjoaquina.errorlogger.service;

import br.com.squadjoaquina.errorlogger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EmailServiceImpl {

    @Autowired
    public JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendNewPasswordEmail(User user, String newpasswd){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("ErrorLogger: Solicitação de nova senha");
        message.setSentDate(new Date(System.currentTimeMillis()));
        message.setText("Nova senha: " + newpasswd);
        emailSender.send(message);
    }

}
