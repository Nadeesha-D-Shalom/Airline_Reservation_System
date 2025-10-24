package com.example.airlineReservationApp.service;

import com.example.airlineReservationApp.model.LoyaltyPoints;
import com.example.airlineReservationApp.model.Payment;
import com.example.airlineReservationApp.repository.LoyaltyPointsRepository;
import com.example.airlineReservationApp.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private LoyaltyPointsRepository loyaltyRepo;

    public Payment savePayment(Payment payment) {
        // Save payment first
        Payment saved = paymentRepository.save(payment);

        // Calculate loyalty points (1 point per Rs.100 spent)
        int earned = (int) (payment.getAmount() / 100);

        // Fetch or create loyalty record for this user's email
        LoyaltyPoints lp = loyaltyRepo.findByEmail(payment.getUserEmail())
                .orElseGet(() -> {
                    LoyaltyPoints newLp = new LoyaltyPoints(payment.getUserEmail());
                    newLp.setPoints(0);
                    return newLp;
                });

        // Add new points
        lp.setPoints(lp.getPoints() + earned);

        // Determine membership status (optional enhancement)
        String status;
        if (lp.getPoints() >= 5000) {
            status = "Platinum";
        } else if (lp.getPoints() >= 2000) {
            status = "Gold";
        } else {
            status = "Bronze";
        }

        lp.setStatus(status); // add this only if you have a 'status' field

        // Save updated loyalty data
        loyaltyRepo.save(lp);

        return saved;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}
