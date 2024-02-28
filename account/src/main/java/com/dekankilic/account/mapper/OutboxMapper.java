package com.dekankilic.account.mapper;

import com.dekankilic.account.model.Account;
import com.dekankilic.account.model.Outbox;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OutboxMapper {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static Outbox mapToOutboxFromAccount(Account account) {
        try {
            String payload = MAPPER.writeValueAsString(account);
            return Outbox.builder()
                    .type("Account Created")
                    .payload(payload)
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
