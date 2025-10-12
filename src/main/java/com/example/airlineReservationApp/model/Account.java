package com.example.airlineReservationApp.model;

<<<<<<< HEAD
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; // USER or ADMIN
=======
public interface Account {
    String getEmail();
    String getPassword();

    // return the enum type instead of String
    BaseUser.Role getRole();

    default String getRoleEnum() {
        return getRole().name();
    }
>>>>>>> 4a26394 (Version 1.8.1)
}
