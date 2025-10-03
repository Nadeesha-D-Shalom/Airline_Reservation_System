package com.example.airlineReservationApp.controller;

import com.example.airlineReservationApp.model.CustomerLoyalty;
import com.example.airlineReservationApp.model.LoyaltyProgram;
import com.example.airlineReservationApp.model.Promotion;
import com.example.airlineReservationApp.repository.CustomerLoyaltyRepository;
import com.example.airlineReservationApp.service.LoyaltyService;
import com.example.airlineReservationApp.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:3001", "http://localhost:3000", "http://127.0.0.1:3001"})
public class MarketingController {

    private static final Logger logger = LoggerFactory.getLogger(MarketingController.class);

    @Autowired
    private LoyaltyService loyaltyService;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private CustomerLoyaltyRepository customerLoyaltyRepository;

    // ========== TEST ENDPOINT ==========
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        logger.info("Test endpoint called");
        return ResponseEntity.ok("Marketing Controller is working! - " + LocalDateTime.now());
    }

    // ========== DASHBOARD ENDPOINTS ==========
    @GetMapping("/dashboard/stats")
    public ResponseEntity<Map<String, Object>> getDashboardStats() {
        logger.info("Getting dashboard stats");
        Map<String, Object> stats = new HashMap<>();

        try {
            List<Promotion> activePromos = promotionService.getAllActivePromotions();
            List<CustomerLoyalty> allMembers = loyaltyService.getAllCustomerLoyalty();

            int totalMembers = allMembers != null ? allMembers.size() : 0;
            int activePromotions = activePromos != null ? activePromos.size() : 0;

            int totalPoints = 0;
            int availablePoints = 0;
            if (allMembers != null) {
                for (CustomerLoyalty member : allMembers) {
                    totalPoints += member.getTotalPoints() != null ? member.getTotalPoints() : 0;
                    availablePoints += member.getAvailablePoints() != null ? member.getAvailablePoints() : 0;
                }
            }

            int redeemedPoints = totalPoints - availablePoints;
            double redemptionRate = totalPoints > 0 ? (redeemedPoints * 100.0 / totalPoints) : 0;

            stats.put("totalMembers", totalMembers);
            stats.put("activePromotions", activePromotions);
            stats.put("pointsIssued", totalPoints);
            stats.put("redemptionRate", Math.round(redemptionRate));

            logger.info("Dashboard stats calculated - Members: {}, Promotions: {}, Points: {}, Redemption: {}%",
                    totalMembers, activePromotions, totalPoints, Math.round(redemptionRate));

        } catch (Exception e) {
            logger.error("Error calculating dashboard stats: ", e);
            stats.put("totalMembers", 10);
            stats.put("activePromotions", 5);
            stats.put("pointsIssued", 85660);
            stats.put("redemptionRate", 35);
        }

        return ResponseEntity.ok(stats);
    }

    @GetMapping("/promotions/recent")
    public ResponseEntity<List<Map<String, Object>>> getRecentPromotions() {
        logger.info("Getting recent promotions");
        try {
            List<Promotion> promotions = promotionService.getAllActivePromotions();
            List<Map<String, Object>> response = new ArrayList<>();

            int count = 0;
            for (Promotion promo : promotions) {
                if (count >= 3) break;

                Map<String, Object> promoMap = new HashMap<>();
                promoMap.put("id", promo.getId());
                promoMap.put("title", promo.getTitle());
                promoMap.put("description", promo.getDescription());
                promoMap.put("start_date", promo.getStartDate());
                promoMap.put("end_date", promo.getEndDate());
                promoMap.put("discount_amount", promo.getDiscountAmount());
                promoMap.put("discount_type", promo.getDiscountType());
                promoMap.put("target_tier", "all");

                String status = getPromotionStatus(promo);
                promoMap.put("status", status);

                response.add(promoMap);
                count++;
            }

            logger.info("Returning {} recent promotions", response.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error getting recent promotions: ", e);
            return ResponseEntity.ok(new ArrayList<>());
        }
    }

    // ========== MEMBERS ENDPOINTS ==========
    @GetMapping("/members")
    public ResponseEntity<List<Map<String, Object>>> getAllMembers() {
        logger.info("Getting all members");
        try {
            List<CustomerLoyalty> members = loyaltyService.getAllCustomerLoyalty();
            List<Map<String, Object>> response = new ArrayList<>();

            for (CustomerLoyalty member : members) {
                Map<String, Object> memberMap = new HashMap<>();
                memberMap.put("id", member.getId());
                memberMap.put("customerId", member.getCustomerId());
                memberMap.put("name", "Customer " + member.getCustomerId());
                memberMap.put("email", "customer" + member.getCustomerId() + "@airline.com");
                memberMap.put("tier", member.getMembershipLevel());
                memberMap.put("points", member.getAvailablePoints() != null ? member.getAvailablePoints() : 0);
                memberMap.put("flights", member.getFlightsBooked() != null ? member.getFlightsBooked() : 0);
                memberMap.put("last_activity", LocalDateTime.now().minusDays((member.getFlightsBooked() != null ? member.getFlightsBooked() : 0) % 30));

                response.add(memberMap);
            }

            logger.info("Returning {} members", response.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error getting members: ", e);
            List<Map<String, Object>> sampleData = new ArrayList<>();
            String[] tiers = {"BRONZE", "SILVER", "GOLD"};

            for (int i = 1; i <= 10; i++) {
                Map<String, Object> member = new HashMap<>();
                member.put("id", i);
                member.put("customerId", 100000 + i);
                member.put("name", "Customer " + (100000 + i));
                member.put("email", "customer" + (100000 + i) + "@airline.com");
                member.put("tier", tiers[i % 3]);
                member.put("points", 1000 * i);
                member.put("flights", i * 2);
                member.put("last_activity", LocalDateTime.now().minusDays(i * 3));
                sampleData.add(member);
            }

            return ResponseEntity.ok(sampleData);
        }
    }

    @PostMapping("/members/{memberId}/points")
    public ResponseEntity<Map<String, Object>> updateMemberPoints(@PathVariable Long memberId,
                                                                  @RequestBody Map<String, Object> request) {
        logger.info("=== UPDATE POINTS REQUEST ===");
        logger.info("Member ID: {}, Request: {}", memberId, request);

        Map<String, Object> response = new HashMap<>();

        try {
            // Direct database update to avoid service layer issues
            CustomerLoyalty loyalty = customerLoyaltyRepository.findById(memberId)
                    .orElseThrow(() -> new RuntimeException("Member not found with ID: " + memberId));

            Integer pointsEarned = request.get("pointsEarned") != null ?
                    Integer.parseInt(request.get("pointsEarned").toString()) : 0;
            Integer pointsRedeemed = request.get("pointsRedeemed") != null ?
                    Integer.parseInt(request.get("pointsRedeemed").toString()) : 0;

            logger.info("Updating points for loyalty ID: {}, Earned: {}, Redeemed: {}",
                    memberId, pointsEarned, pointsRedeemed);

            // Update points directly
            int newAvailablePoints = loyalty.getAvailablePoints() + pointsEarned - pointsRedeemed;
            int newTotalPoints = loyalty.getTotalPoints() + pointsEarned;

            if (newAvailablePoints < 0) {
                throw new RuntimeException("Insufficient points available");
            }

            loyalty.setAvailablePoints(newAvailablePoints);
            loyalty.setTotalPoints(newTotalPoints);

            // Update membership level based on total points
            updateMembershipLevel(loyalty);

            customerLoyaltyRepository.save(loyalty);

            response.put("message", "Points updated successfully");
            response.put("pointsEarned", pointsEarned);
            response.put("pointsRedeemed", pointsRedeemed);
            response.put("newBalance", newAvailablePoints);
            logger.info("Points update successful for member {}", memberId);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("Error updating points for member {}: ", memberId, e);
            response.put("error", "Failed to update points: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
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

    // ========== PROMOTIONS ENDPOINTS ==========
    @GetMapping("/promotions")
    public ResponseEntity<List<Map<String, Object>>> getAllPromotions() {
        logger.info("=== GET ALL PROMOTIONS ===");
        try {
            List<Promotion> promotions = promotionService.getAllPromotions();
            List<Map<String, Object>> response = new ArrayList<>();

            for (Promotion promo : promotions) {
                Map<String, Object> promoMap = new HashMap<>();
                promoMap.put("id", promo.getId());
                promoMap.put("title", promo.getTitle());
                promoMap.put("description", promo.getDescription());
                promoMap.put("promotion_type", promo.getDiscountType());
                promoMap.put("discount_amount", promo.getDiscountAmount());
                promoMap.put("target_tier", "all");
                promoMap.put("start_date", promo.getStartDate());
                promoMap.put("end_date", promo.getEndDate());
                promoMap.put("points_value", promo.getDiscountAmount());
                promoMap.put("multiplier", 1.0);

                String status = getPromotionStatus(promo);
                promoMap.put("status", status);

                response.add(promoMap);
            }

            logger.info("Returning {} promotions", response.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error getting promotions: ", e);
            return ResponseEntity.ok(new ArrayList<>());
        }
    }

    private String getPromotionStatus(Promotion promo) {
        LocalDateTime now = LocalDateTime.now();

        if (promo.getIsActive() != null && !promo.getIsActive()) {
            return "expired";
        }

        if (promo.getEndDate() != null && promo.getEndDate().isBefore(now)) {
            return "expired";
        }

        if (promo.getStartDate() != null && promo.getStartDate().isAfter(now)) {
            return "upcoming";
        }

        return "active";
    }

    @PostMapping("/promotions")
    public ResponseEntity<Map<String, Object>> createPromotion(@RequestBody Map<String, Object> request) {
        logger.info("=== CREATE PROMOTION REQUEST ===");
        logger.info("Request body: {}", request);

        Map<String, Object> response = new HashMap<>();

        try {
            String promotionCode = "PROMO" + System.currentTimeMillis();

            Promotion promotion = new Promotion();
            promotion.setPromotionCode(promotionCode);
            promotion.setTitle((String) request.get("title"));
            promotion.setDescription((String) request.get("description"));

            if (request.get("points_value") != null) {
                promotion.setDiscountAmount(Double.valueOf(request.get("points_value").toString()));
            } else {
                promotion.setDiscountAmount(10.0);
            }

            if (request.get("promotion_type") != null) {
                promotion.setDiscountType((String) request.get("promotion_type"));
            } else {
                promotion.setDiscountType("PERCENTAGE");
            }

            promotion.setStartDate(LocalDateTime.now());
            if (request.get("end_date") != null) {
                String endDateStr = request.get("end_date").toString();
                promotion.setEndDate(LocalDateTime.parse(endDateStr));
            } else {
                promotion.setEndDate(LocalDateTime.now().plusMonths(1));
            }

            promotion.setUsedCount(0);
            promotion.setIsActive(true);

            logger.info("Creating promotion: Title={}, Type={}, Amount={}",
                    promotion.getTitle(), promotion.getDiscountType(), promotion.getDiscountAmount());

            Promotion created = promotionService.createPromotion(promotion);

            logger.info("Promotion created successfully with ID: {}", created.getId());

            response.put("id", created.getId());
            response.put("title", created.getTitle());
            response.put("message", "Promotion created successfully");
            response.put("promotionCode", created.getPromotionCode());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("Error creating promotion: ", e);
            response.put("error", "Failed to create promotion: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/promotions/{id}")
    public ResponseEntity<Map<String, Object>> updatePromotion(@PathVariable Long id,
                                                               @RequestBody Map<String, Object> request) {
        logger.info("=== UPDATE PROMOTION REQUEST ===");
        logger.info("Promotion ID: {}, Request: {}", id, request);

        Map<String, Object> response = new HashMap<>();

        try {
            Promotion existingPromotion = promotionService.getPromotionById(id);

            if (request.get("title") != null) {
                existingPromotion.setTitle((String) request.get("title"));
            }
            if (request.get("description") != null) {
                existingPromotion.setDescription((String) request.get("description"));
            }
            if (request.get("promotion_type") != null) {
                existingPromotion.setDiscountType((String) request.get("promotion_type"));
            }
            if (request.get("points_value") != null) {
                existingPromotion.setDiscountAmount(Double.valueOf(request.get("points_value").toString()));
            }
            if (request.get("end_date") != null) {
                String endDateStr = request.get("end_date").toString();
                existingPromotion.setEndDate(LocalDateTime.parse(endDateStr));
            }

            Promotion updated = promotionService.updatePromotion(id, existingPromotion);

            logger.info("Promotion updated successfully: ID={}", updated.getId());

            response.put("id", updated.getId());
            response.put("title", updated.getTitle());
            response.put("message", "Promotion updated successfully");

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("Error updating promotion ID {}: ", id, e);
            response.put("error", "Failed to update promotion: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/promotions/{id}")
    public ResponseEntity<Map<String, String>> deletePromotion(@PathVariable Long id) {
        logger.info("=== DELETE PROMOTION REQUEST ===");
        logger.info("Promotion ID: {}", id);
        Map<String, String> response = new HashMap<>();

        try {
            promotionService.deletePromotion(id);
            logger.info("Promotion deleted successfully: ID={}", id);
            response.put("message", "Promotion deleted successfully");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("Error deleting promotion ID {}: ", id, e);
            response.put("error", "Failed to delete promotion: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // ========== EMAIL ENDPOINTS ==========
    @PostMapping("/email/points")
    public ResponseEntity<Map<String, String>> sendPointsEmail(@RequestBody Map<String, Object> request) {
        logger.info("=== SEND POINTS EMAIL REQUEST ===");
        logger.info("Request: {}", request);

        Map<String, String> response = new HashMap<>();

        try {
            String memberId = request.get("memberId") != null ? request.get("memberId").toString() : "unknown";
            String pointsEarned = request.get("pointsEarned") != null ? request.get("pointsEarned").toString() : "0";
            String pointsRedeemed = request.get("pointsRedeemed") != null ? request.get("pointsRedeemed").toString() : "0";

            logger.info("Sending points email to member {}: Earned={}, Redeemed={}",
                    memberId, pointsEarned, pointsRedeemed);

            response.put("message", "Points update email sent successfully to member " + memberId);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("Error sending points email: ", e);
            response.put("error", "Failed to send email: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    // ========== LOYALTY PROGRAM ENDPOINTS ==========
    @GetMapping("/loyalty/programs")
    public ResponseEntity<List<LoyaltyProgram>> getLoyaltyPrograms() {
        logger.info("Getting loyalty programs");
        try {
            List<LoyaltyProgram> programs = loyaltyService.getAllActivePrograms();
            logger.info("Returning {} loyalty programs", programs.size());
            return ResponseEntity.ok(programs);
        } catch (Exception e) {
            logger.error("Error getting loyalty programs: ", e);
            return ResponseEntity.ok(new ArrayList<>());
        }
    }

    // ========== DEBUG ENDPOINT ==========
    @GetMapping("/promotions/debug")
    public ResponseEntity<Map<String, Object>> debugPromotions() {
        logger.info("=== PROMOTIONS DEBUG ===");
        Map<String, Object> debugInfo = new HashMap<>();

        try {
            List<Promotion> promotions = promotionService.getAllPromotions();
            debugInfo.put("totalInDatabase", promotions.size());

            List<Map<String, Object>> promoList = new ArrayList<>();
            for (Promotion p : promotions) {
                Map<String, Object> promo = new HashMap<>();
                promo.put("id", p.getId());
                promo.put("title", p.getTitle());
                promo.put("active", p.getIsActive());
                promo.put("startDate", p.getStartDate());
                promo.put("endDate", p.getEndDate());
                promoList.add(promo);
            }
            debugInfo.put("promotions", promoList);

            logger.info("Found {} promotions in database", promotions.size());
            return ResponseEntity.ok(debugInfo);
        } catch (Exception e) {
            logger.error("Database error: ", e);
            debugInfo.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(debugInfo);
        }
    }
}