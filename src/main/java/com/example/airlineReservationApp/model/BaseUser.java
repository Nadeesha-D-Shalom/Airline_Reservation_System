package com.example.airlineReservationApp.model;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class BaseUser implements Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long userID;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role { USER, ADMIN }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getRole() {
        return role != null ? role.name() : null;
    }

    // Standard getters and setters
    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    //  This is the missing method your service expects
    public Role getRoleEnum() {
        return role;
    }
}
