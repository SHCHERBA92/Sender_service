package com.example.sender_service.service.sender;

public interface Sender {
    void sendMessToRegistration(String to, String code);

    void sendMessToInfo(String to);
}
