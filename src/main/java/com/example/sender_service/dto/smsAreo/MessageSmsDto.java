package com.example.sender_service.dto.smsAreo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageSmsDto {
    private String numberPhone;
    private String code;
    private StatusMessage statusSMS;
}
