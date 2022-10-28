package com.example.sender_service.configuration;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageForMail implements Serializable {
    private String email;
    private String kod;
}
