package com.example.sender_service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSender {
    private final JavaMailSender javaMailSender;

    @Value("${mail.user_name}")
    private String FROM_MAIL;

    private final String SUBJECT = "Подтвердите доступ";
    private final String URL = "http://localhost:8081/check/code";

    public MailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMailToAddress(String emailTo, String code){

        String text = String.format("Вы зарегестрировались на портале \" Fire-YOGA \"," +
                " для завершения регистрации перейдите по ссылке %s и введите секретный код = %s", URL, code);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(FROM_MAIL);
        simpleMailMessage.setTo(emailTo);
        simpleMailMessage.setSubject(SUBJECT);
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
    }
}
