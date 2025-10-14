package com.example.airlineReservationApp.model;

public enum ComplaintStatus {
    PENDING,       // Submitted by user; not yet handled
    IN_REVIEW,     // Picked up by staff/admin
    RESOLVED,      // Fixed; awaiting user confirmation (optional)
    CLOSED         // Fully closed
}
