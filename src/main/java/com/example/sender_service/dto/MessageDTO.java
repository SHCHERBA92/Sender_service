package com.example.sender_service.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageDTO {
    private String email;
    private String kod;
}
