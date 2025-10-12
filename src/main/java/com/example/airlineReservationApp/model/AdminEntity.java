package com.example.airlineReservationApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "admins")
public class AdminEntity extends BaseUser {

    @Column(name = "department")
    private String department;

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}
