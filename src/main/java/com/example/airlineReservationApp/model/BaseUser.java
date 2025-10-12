package com.example.airlineReservationApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    private Long id; // use Long (not Integer)
=======
    @Column(name = "id")
    private Integer id;
>>>>>>> 4a26394 (Version 1.8.1)

    @Column(name = "email", nullable = false, unique = true)
    private String email;

<<<<<<< HEAD
    @Column(name = "full_name", nullable = true)
    private String fullName;


    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; // USER, ADMIN, etc.
=======
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    public enum Role {
        USER, ADMIN
    }

    // Getters & Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    @Override
    public String getRoleEnum() {
        return role.name();
    }
>>>>>>> 4a26394 (Version 1.8.1)
}
