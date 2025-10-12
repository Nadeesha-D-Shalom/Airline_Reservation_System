package com.example.airlineReservationApp.model;

public interface Account {
    String getEmail();
    String getPassword();

    // return the enum type instead of String
    BaseUser.Role getRole();

    default String getRoleEnum() {
        return getRole().name();
    }
}
