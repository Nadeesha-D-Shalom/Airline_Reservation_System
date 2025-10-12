package com.example.airlineReservationApp.dto;

public class AuthResponse {
<<<<<<< HEAD

=======
>>>>>>> 4a26394 (Version 1.8.1)
    private String token;
    private String role;

    public AuthResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

    public String getToken() { return token; }
<<<<<<< HEAD
    public void setToken(String token) { this.token = token; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
=======
    public String getRole() { return role; }
>>>>>>> 4a26394 (Version 1.8.1)
}
