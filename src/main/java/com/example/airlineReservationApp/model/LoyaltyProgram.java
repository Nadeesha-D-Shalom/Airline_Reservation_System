package com.example.airlineReservationApp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "loyalty_programs")
public class LoyaltyProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String programName;

    private String description;

    @Column(nullable = false)
    private Integer pointsPerDollar;

    @Column(nullable = false)
    private Double discountPercentage;

    @Column(nullable = false)
    private String membershipLevel;

    private LocalDateTime createdDate;
    private LocalDateTime expiryDate;
    private Boolean isActive;

    // Constructors
    public LoyaltyProgram() {}

    public LoyaltyProgram(String programName, String description, Integer pointsPerDollar,
                          Double discountPercentage, String membershipLevel) {
        this.programName = programName;
        this.description = description;
        this.pointsPerDollar = pointsPerDollar;
        this.discountPercentage = discountPercentage;
        this.membershipLevel = membershipLevel;
        this.createdDate = LocalDateTime.now();
        this.isActive = true;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProgramName() { return programName; }
    public void setProgramName(String programName) { this.programName = programName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getPointsPerDollar() { return pointsPerDollar; }
    public void setPointsPerDollar(Integer pointsPerDollar) { this.pointsPerDollar = pointsPerDollar; }

    public Double getDiscountPercentage() { return discountPercentage; }
    public void setDiscountPercentage(Double discountPercentage) { this.discountPercentage = discountPercentage; }

    public String getMembershipLevel() { return membershipLevel; }
    public void setMembershipLevel(String membershipLevel) { this.membershipLevel = membershipLevel; }

    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }

    public LocalDateTime getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}