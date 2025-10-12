package com.example.airlineReservationApp.service;

import com.example.airlineReservationApp.model.Account;
import com.example.airlineReservationApp.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account register(Account account) {
        if (accountRepository.existsByEmail(account.getEmail())) {
            throw new RuntimeException("Email already registered!");
        }
        return accountRepository.save(account);
    }

    public Account getByEmail(String email) {
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Account not found with email: " + email));
    }

    public boolean existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }
}
