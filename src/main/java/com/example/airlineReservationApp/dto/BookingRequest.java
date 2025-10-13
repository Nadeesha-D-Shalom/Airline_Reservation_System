package com.example.airlineReservationApp.dto;

import java.util.List;
import lombok.Data;

@Data
public class BookingRequest {
    private Long flightId;
    private List<PassengerPayload> passengers;
    private double totalAmount;
    private String transactionId;
    private String status;

    @Data
    public static class PassengerPayload {
        private String fullName;
        private String dob;
        private String gender;
        private String nationality;
        private String email;
        private String phone;
        private String passport;
        private String flyerNumber;
        private String assistance;
        private String dietary;
        private boolean insurance;
        private String baggage;
        private String emergencyName;
        private String emergencyPhone;
        private String seat;
    }
}
