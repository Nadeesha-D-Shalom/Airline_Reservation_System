package com.example.airlineReservationApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity extends BaseUser {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    // ✅ Make sure we actually store full_name in BaseUser
    @Override
    public void setFullName(String fullName) {
        super.setFullName(fullName); // sets full_name in BaseUser

        // Optional: keep first and last in sync
        if (fullName != null) {
            String[] parts = fullName.trim().split(" ", 2);
            this.firstName = parts[0];
            if (parts.length > 1) this.lastName = parts[1];
        }
    }

    @Override
    public String getFullName() {
        String name = super.getFullName();
        if (name != null && !name.isBlank()) return name;
        return (firstName != null ? firstName : "") + (lastName != null ? " " + lastName : "");
    }

    // Getters and Setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
