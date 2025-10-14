package com.example.airlineReservationApp.repository;

import com.example.airlineReservationApp.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByUserEmail(String userEmail);
}
