package com.dekankilic.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class AccountCreateResponse {
    private String username;
    private String email;
    private Date createdAt;
}
