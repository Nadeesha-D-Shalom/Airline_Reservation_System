package com.example.airlineReservationApp.service;

import com.example.airlineReservationApp.model.Account;

public interface AuthService {

    // Handles both User and Admin login
    String login(String email, String password);

    // Unified registration for both UserEntity and AdminEntity (via Account interface)
    Account register(Account account);

    // Checks whether email exists in User or Admin table
    boolean emailExists(String email);
}
