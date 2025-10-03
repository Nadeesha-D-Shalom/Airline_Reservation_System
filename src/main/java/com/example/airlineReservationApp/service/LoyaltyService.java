package com.example.airlineReservationApp.service;

import com.example.airlineReservationApp.model.CustomerLoyalty;
import com.example.airlineReservationApp.model.LoyaltyProgram;
import com.example.airlineReservationApp.repository.CustomerLoyaltyRepository;
import com.example.airlineReservationApp.repository.LoyaltyProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LoyaltyService {

    @Autowired
    private CustomerLoyaltyRepository customerLoyaltyRepository;

    @Autowired
    private LoyaltyProgramRepository loyaltyProgramRepository;

    public CustomerLoyalty joinLoyaltyProgram(Long customerId) {
        Optional<CustomerLoyalty> existing = customerLoyaltyRepository.findByCustomerId(customerId);
        if (existing.isPresent()) {
            return existing.get();
        }

        CustomerLoyalty newMembership = new CustomerLoyalty(customerId);
        return customerLoyaltyRepository.save(newMembership);
    }

    public CustomerLoyalty addPoints(Long customerId, Integer points, Double amountSpent) {
        CustomerLoyalty loyalty = customerLoyaltyRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found in loyalty program"));

        loyalty.setTotalPoints(loyalty.getTotalPoints() + points);
        loyalty.setAvailablePoints(loyalty.getAvailablePoints() + points);
        loyalty.setTotalSpent(loyalty.getTotalSpent() + amountSpent);
        loyalty.setFlightsBooked(loyalty.getFlightsBooked() + 1);

        updateMembershipLevel(loyalty);
        return customerLoyaltyRepository.save(loyalty);
    }

    private void updateMembershipLevel(CustomerLoyalty loyalty) {
        String newLevel = "BRONZE";
        if (loyalty.getTotalPoints() >= 10000) {
            newLevel = "GOLD";
        } else if (loyalty.getTotalPoints() >= 5000) {
            newLevel = "SILVER";
        }
        loyalty.setMembershipLevel(newLevel);
    }

    public CustomerLoyalty redeemPoints(Long customerId, Integer pointsToRedeem) {
        CustomerLoyalty loyalty = customerLoyaltyRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found in loyalty program"));

        if (loyalty.getAvailablePoints() < pointsToRedeem) {
            throw new RuntimeException("Insufficient points");
        }

        loyalty.setAvailablePoints(loyalty.getAvailablePoints() - pointsToRedeem);
        return customerLoyaltyRepository.save(loyalty);
    }

    public List<LoyaltyProgram> getAllActivePrograms() {
        return loyaltyProgramRepository.findByIsActiveTrue();
    }

    public CustomerLoyalty getCustomerLoyalty(Long customerId) {
        return customerLoyaltyRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found in loyalty program"));
    }

    // ADD THIS METHOD - FIX FOR POINTS UPDATE
    public CustomerLoyalty getCustomerLoyaltyById(Long id) {
        return customerLoyaltyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer loyalty record not found"));
    }

    public List<CustomerLoyalty> getAllCustomerLoyalty() {
        return customerLoyaltyRepository.findAll();
    }

    public LoyaltyProgram createLoyaltyProgram(LoyaltyProgram program) {
        return loyaltyProgramRepository.save(program);
    }

    public void deleteLoyaltyProgram(Long id) {
        LoyaltyProgram program = loyaltyProgramRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Loyalty program not found"));
        program.setIsActive(false);
        loyaltyProgramRepository.save(program);
    }
}