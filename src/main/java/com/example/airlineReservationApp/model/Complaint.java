package com.example.airlineReservationApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The email of the logged-in user who submitted the complaint
    @Column(nullable = false, length = 180)
    private String userEmail;

    // Optional: link the complaint to a specific booking
    private Long bookingId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 40)
    private ComplaintCategory category;

    @NotBlank
    @Size(max = 120)
    @Column(nullable = false, length = 120)
    private String title;

    @NotBlank
    @Size(max = 4000)
    @Column(nullable = false, length = 4000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private ComplaintStatus status = ComplaintStatus.PENDING;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Complaint() {
    }

    public Complaint(String userEmail,
                     Long bookingId,
                     ComplaintCategory category,
                     String title,
                     String description) {
        this.userEmail = userEmail;
        this.bookingId = bookingId;
        this.category = category;
        this.title = title;
        this.description = description;
        this.status = ComplaintStatus.PENDING;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public ComplaintCategory getCategory() {
        return category;
    }

    public void setCategory(ComplaintCategory category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ComplaintStatus getStatus() {
        return status;
    }

    public void setStatus(ComplaintStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
