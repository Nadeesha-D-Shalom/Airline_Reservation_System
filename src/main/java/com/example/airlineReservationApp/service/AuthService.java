package com.example.airlineReservationApp.service;

import com.example.airlineReservationApp.model.Account;

public interface AuthService {
    String login(String email, String password);
    Account register(Account account);
}
