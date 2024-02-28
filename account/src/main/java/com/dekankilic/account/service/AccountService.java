package com.dekankilic.account.service;

import com.dekankilic.account.dto.AccountCreateRequest;
import com.dekankilic.account.dto.AccountCreateResponse;
import com.dekankilic.account.mapper.AccountMapper;
import com.dekankilic.account.mapper.OutboxMapper;
import com.dekankilic.account.model.Account;
import com.dekankilic.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final OutboxService outboxService;

    @Transactional
    public AccountCreateResponse createAccount(AccountCreateRequest request) {
        Account account = AccountMapper.mapToAccountFromRequest(request);
        Account savedAccount = accountRepository.save(account);
        outboxService.createOutbox(OutboxMapper.mapToOutboxFromAccount(savedAccount));

        return AccountMapper.mapToResponseFromAccount(savedAccount);
    }

}
