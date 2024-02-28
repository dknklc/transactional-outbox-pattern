package com.dekankilic.account.mapper;


import com.dekankilic.account.dto.AccountCreateRequest;
import com.dekankilic.account.dto.AccountCreateResponse;
import com.dekankilic.account.model.Account;

import java.sql.Date;
import java.time.Instant;


public final class AccountMapper {

    public static Account mapToAccountFromRequest(AccountCreateRequest accountCreateRequest){
        return Account.builder()
                .username(accountCreateRequest.getUsername())
                .password(accountCreateRequest.getPassword())
                .email(accountCreateRequest.getEmail())
                .createdAt(Date.from(Instant.now()))
                .build();

    }
    public static AccountCreateResponse mapToResponseFromAccount(Account account){
        return AccountCreateResponse.builder()
                .username(account.getUsername())
                .email(account.getEmail())
                .createdAt(account.getCreatedAt())
                .build();
    }
}
