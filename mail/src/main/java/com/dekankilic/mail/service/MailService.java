package com.dekankilic.mail.service;

import com.dekankilic.mail.dto.OutboxPayload;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

    private final ObjectMapper MAPPER = new ObjectMapper();


    @KafkaListener(topics = "mysqlcapturedchanges", groupId = "mail-group")
    public void listener(@Payload Object event, ConsumerRecord consumerRecord) throws Exception{
        String value = (String) consumerRecord.value();

        JsonNode payload = MAPPER.readTree(value);
        log.info("JSON NODE : {}", payload);

        OutboxPayload outboxPayload = MAPPER.readValue(payload.get("payload").asText(), OutboxPayload.class);
        log.info("KafkaPayload: {}", outboxPayload);
        log.info("KafkaPayload getId: {}", outboxPayload.getId());

        log.info("Hello, {}", outboxPayload.getUsername());
    }
}
