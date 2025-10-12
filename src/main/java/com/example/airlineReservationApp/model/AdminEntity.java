package com.example.airlineReservationApp.model;

<<<<<<< HEAD
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
=======
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
>>>>>>> 4a26394 (Version 1.8.1)

@Entity
@Table(name = "admins")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String department;

<<<<<<< HEAD
    @Column(nullable = false)
    private String role = "ADMIN";  // Fixed: use string instead of Role enum
=======
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
>>>>>>> 4a26394 (Version 1.8.1)
}
