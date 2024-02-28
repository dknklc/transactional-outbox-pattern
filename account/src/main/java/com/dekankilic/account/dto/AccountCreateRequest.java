package com.dekankilic.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AccountCreateRequest {
    private String username;
    private String password;
    private String email;


}
