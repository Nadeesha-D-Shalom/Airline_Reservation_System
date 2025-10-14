package com.example.airlineReservationApp.controller;

import com.example.airlineReservationApp.dto.ComplaintDTO;
import com.example.airlineReservationApp.model.Complaint;
import com.example.airlineReservationApp.model.ComplaintStatus;
import com.example.airlineReservationApp.repository.ComplaintRepository;
import com.example.airlineReservationApp.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/complaints")
@CrossOrigin(origins = "http://localhost:3000") // adjust if needed
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;
    private ComplaintRepository complaintRepository;

    // Create new complaint (User)
    @PostMapping("/add")
    public ResponseEntity<ComplaintDTO> createComplaint(@RequestBody Complaint complaint) {
        ComplaintDTO saved = complaintService.createComplaint(complaint);
        return ResponseEntity.ok(saved);
    }

    // Get all complaints (Admin)
    @GetMapping
    public ResponseEntity<List<ComplaintDTO>> getAllComplaints() {
        return ResponseEntity.ok(complaintService.getAllComplaints());
    }

    // Get complaints by user email
    @GetMapping("/user/{email}")
    public ResponseEntity<List<ComplaintDTO>> getUserComplaints(@PathVariable String email) {
        return ResponseEntity.ok(complaintService.getComplaintsByUser(email));
    }

    // Update status (Admin)
    @PutMapping("/{id}/status")
    public ResponseEntity<ComplaintDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam("status") ComplaintStatus status) {
        ComplaintDTO updated = complaintService.updateComplaintStatus(id, status);
        return ResponseEntity.ok(updated);
    }

    // Delete complaint (Admin or cleanup)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComplaint(@PathVariable Long id) {
        complaintService.deleteComplaint(id);
        return ResponseEntity.ok("Complaint deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComplaint(@PathVariable Long id, @RequestBody Complaint updatedComplaint) {
        Optional<Complaint> optional = complaintRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(404).body("Complaint not found");
        }

        Complaint complaint = optional.get();

        // Restrict editing once complaint is in review or beyond
        if (complaint.getStatus() != ComplaintStatus.PENDING) {
            return ResponseEntity.status(403).body("You cannot edit this complaint once it is under review or resolved.");
        }

        // Allow editing title, description, and category
        complaint.setTitle(updatedComplaint.getTitle());
        complaint.setDescription(updatedComplaint.getDescription());
        complaint.setCategory(updatedComplaint.getCategory());

        Complaint saved = complaintRepository.save(complaint);
        return ResponseEntity.ok(saved);
    }

}
