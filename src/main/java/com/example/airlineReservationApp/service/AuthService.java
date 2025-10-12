package com.example.airlineReservationApp.service;

import com.example.airlineReservationApp.model.Account;
import com.example.airlineReservationApp.model.UserEntity;
import com.example.airlineReservationApp.dto.AuthRequest;
import com.example.airlineReservationApp.dto.AuthResponse;

public interface AuthService {
    boolean emailExists(String email);
    Account register(Account account);
    String login(String email, String password);
}
