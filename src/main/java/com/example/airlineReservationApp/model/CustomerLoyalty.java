package com.example.airlineReservationApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_loyalty")
public class CustomerLoyalty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private String membershipLevel = "BRONZE";

    private Integer totalPoints = 0;
    private Integer availablePoints = 0;
    private Integer flightsBooked = 0;
    private Double totalSpent = 0.0;

    // Constructors
    public CustomerLoyalty() {}

    public CustomerLoyalty(Long customerId) {
        this.customerId = customerId;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public String getMembershipLevel() { return membershipLevel; }
    public void setMembershipLevel(String membershipLevel) { this.membershipLevel = membershipLevel; }

    public Integer getTotalPoints() { return totalPoints; }
    public void setTotalPoints(Integer totalPoints) { this.totalPoints = totalPoints; }

    public Integer getAvailablePoints() { return availablePoints; }
    public void setAvailablePoints(Integer availablePoints) { this.availablePoints = availablePoints; }

    public Integer getFlightsBooked() { return flightsBooked; }
    public void setFlightsBooked(Integer flightsBooked) { this.flightsBooked = flightsBooked; }

    public Double getTotalSpent() { return totalSpent; }
    public void setTotalSpent(Double totalSpent) { this.totalSpent = totalSpent; }
}