package com.example.airlineReservationApp.dto;

import java.util.List;

public class BookingRequest {

    private Long flightId;
    private List<PassengerPayload> passengers;
    private double totalAmount;
    private String transactionId;
    private String status;

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public List<PassengerPayload> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<PassengerPayload> passengers) {
        this.passengers = passengers;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Nested payload for passenger details
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

        public String getFullName() { return fullName; }
        public void setFullName(String fullName) { this.fullName = fullName; }

        public String getDob() { return dob; }
        public void setDob(String dob) { this.dob = dob; }

        public String getGender() { return gender; }
        public void setGender(String gender) { this.gender = gender; }

        public String getNationality() { return nationality; }
        public void setNationality(String nationality) { this.nationality = nationality; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPhone() { return phone; }
        public void setPhone(String phone) { this.phone = phone; }

        public String getPassport() { return passport; }
        public void setPassport(String passport) { this.passport = passport; }

        public String getFlyerNumber() { return flyerNumber; }
        public void setFlyerNumber(String flyerNumber) { this.flyerNumber = flyerNumber; }

        public String getAssistance() { return assistance; }
        public void setAssistance(String assistance) { this.assistance = assistance; }

        public String getDietary() { return dietary; }
        public void setDietary(String dietary) { this.dietary = dietary; }

        public boolean isInsurance() { return insurance; }
        public void setInsurance(boolean insurance) { this.insurance = insurance; }

        public String getBaggage() { return baggage; }
        public void setBaggage(String baggage) { this.baggage = baggage; }

        public String getEmergencyName() { return emergencyName; }
        public void setEmergencyName(String emergencyName) { this.emergencyName = emergencyName; }

        public String getEmergencyPhone() { return emergencyPhone; }
        public void setEmergencyPhone(String emergencyPhone) { this.emergencyPhone = emergencyPhone; }

        public String getSeat() { return seat; }
        public void setSeat(String seat) { this.seat = seat; }
    }
}
