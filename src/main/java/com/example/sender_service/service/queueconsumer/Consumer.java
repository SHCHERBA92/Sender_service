package com.example.sender_service.service.queueconsumer;

import org.springframework.amqp.core.Message;

public interface Consumer {
    void getMessageFromRabbit(Message message);
}
