package com.example.airlineReservationApp.service;

import com.example.airlineReservationApp.dto.ComplaintDTO;
import com.example.airlineReservationApp.model.Complaint;
import com.example.airlineReservationApp.model.ComplaintStatus;
import com.example.airlineReservationApp.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    // Convert Entity → DTO
    private ComplaintDTO mapToDTO(Complaint complaint) {
        return new ComplaintDTO(
                complaint.getId(),
                complaint.getUserEmail(),
                complaint.getBookingId(),
                complaint.getCategory(),
                complaint.getTitle(),
                complaint.getDescription(),
                complaint.getStatus(),
                complaint.getCreatedAt(),
                complaint.getUpdatedAt()
        );
    }

    // Create new complaint
    public ComplaintDTO createComplaint(Complaint complaint) {
        Complaint saved = complaintRepository.save(complaint);
        return mapToDTO(saved);
    }

    // Get all complaints (Admin)
    public List<ComplaintDTO> getAllComplaints() {
        return complaintRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get complaints by user email (User)
    public List<ComplaintDTO> getComplaintsByUser(String email) {
        return complaintRepository.findByUserEmail(email)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Update complaint status (Admin)
    public ComplaintDTO updateComplaintStatus(Long id, ComplaintStatus status) {
        Optional<Complaint> optional = complaintRepository.findById(id);
        if (optional.isEmpty()) {
            throw new RuntimeException("Complaint not found");
        }
        Complaint complaint = optional.get();
        complaint.setStatus(status);
        Complaint updated = complaintRepository.save(complaint);
        return mapToDTO(updated);
    }

    // Delete complaint (Admin or after closing)
    public void deleteComplaint(Long id) {
        complaintRepository.deleteById(id);
    }
}
