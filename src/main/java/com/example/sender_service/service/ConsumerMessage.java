package com.example.sender_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class ConsumerMessage {

    private final ObjectMapper objectMapper;
    private final MailSender mailSender;

    public ConsumerMessage(ObjectMapper objectMapper, MailSender mailSender) {
        this.objectMapper = objectMapper;
        this.mailSender = mailSender;
    }

    @RabbitListener(queues = "queueEmail")
    public void getMessage(Message messageDTO){
        String s = new String(messageDTO.getBody(), StandardCharsets.UTF_8);
        try {
            var json = objectMapper.readTree(s);
            var email = json.get("email").asText();
            var code = json.get("kod").asText();

            mailSender.sendMailToAddress(email, code);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
