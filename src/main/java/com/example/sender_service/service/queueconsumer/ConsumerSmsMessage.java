package com.example.sender_service.service.queueconsumer;

import com.example.sender_service.service.sender.SenderSms;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class ConsumerSmsMessage implements Consumer {

    private final SenderSms senderSms;
    private final ObjectMapper objectMapper;

    public ConsumerSmsMessage(SenderSms senderSms,
                              ObjectMapper objectMapper) {
        this.senderSms = senderSms;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "queueSms")
    @Override
    public void getMessageFromRabbit(Message message) {
        String s = new String(message.getBody(), StandardCharsets.UTF_8);
        try {
            var jsonNode = objectMapper.readTree(s);
            var email = jsonNode.get("toAddress").asText();
            var code = jsonNode.get("code").asText();
            senderSms.sendMessToRegistration(email, code);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
