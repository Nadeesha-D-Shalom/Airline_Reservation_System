package com.example.airlineReservationApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "loyalty_points")
public class LoyaltyPoints {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private int points = 0;

    private String status = "Bronze"; // default membership level

    public LoyaltyPoints() {}

    public LoyaltyPoints(String email) {
        this.email = email;
        this.points = 0;
        this.status = "Bronze";
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
