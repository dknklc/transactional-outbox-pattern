package com.dekankilic.account.service;

import com.dekankilic.account.model.Outbox;
import com.dekankilic.account.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OutboxService {
    private final OutboxRepository outboxRepository;

    public void createOutbox(Outbox outbox) {
        outboxRepository.save(outbox);
    }

}
