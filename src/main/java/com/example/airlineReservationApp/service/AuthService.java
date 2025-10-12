package com.example.airlineReservationApp.service;

import com.example.airlineReservationApp.dto.AuthResponse;
import com.example.airlineReservationApp.model.Account;

public interface AuthService {
    Account register(Account account);
    AuthResponse login(String email, String password);
}
