package com.dekankilic.mail.dto;

import lombok.Data;

@Data
public class OutboxPayload {
    private String id;
    private String username;
    private String email;
    private String password;
    private long createdAt;

}
