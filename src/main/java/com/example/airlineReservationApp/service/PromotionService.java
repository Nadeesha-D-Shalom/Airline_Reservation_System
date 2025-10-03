package com.example.airlineReservationApp.service;

import com.example.airlineReservationApp.model.Promotion;
import com.example.airlineReservationApp.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PromotionService {

    private static final Logger logger = LoggerFactory.getLogger(PromotionService.class);

    @Autowired
    private PromotionRepository promotionRepository;

    public Promotion createPromotion(Promotion promotion) {
        logger.info("=== CREATING PROMOTION ===");
        logger.info("Promotion details - Title: {}, Type: {}, Amount: {}",
                promotion.getTitle(), promotion.getDiscountType(), promotion.getDiscountAmount());

        try {
            // Generate promotion code if not provided
            if (promotion.getPromotionCode() == null) {
                String promotionCode = "PROMO" + System.currentTimeMillis();
                promotion.setPromotionCode(promotionCode);
                logger.info("Generated promotion code: {}", promotionCode);
            }

            // Set default values
            if (promotion.getUsedCount() == null) {
                promotion.setUsedCount(0);
            }
            if (promotion.getIsActive() == null) {
                promotion.setIsActive(true);
            }
            if (promotion.getStartDate() == null) {
                promotion.setStartDate(LocalDateTime.now());
            }

            // Save to database
            Promotion savedPromotion = promotionRepository.save(promotion);
            logger.info("=== PROMOTION CREATED SUCCESSFULLY ===");
            logger.info("Promotion ID: {}, Code: {}", savedPromotion.getId(), savedPromotion.getPromotionCode());

            // Verify it was saved by retrieving it
            Optional<Promotion> verified = promotionRepository.findById(savedPromotion.getId());
            if (verified.isPresent()) {
                logger.info("Promotion verified in database: {}", verified.get().getTitle());
            } else {
                logger.error("Promotion not found after save!");
            }

            return savedPromotion;

        } catch (Exception e) {
            logger.error("=== ERROR CREATING PROMOTION ===", e);
            throw new RuntimeException("Failed to create promotion: " + e.getMessage());
        }
    }

    public List<Promotion> getAllPromotions() {
        logger.info("=== GETTING ALL PROMOTIONS ===");
        try {
            List<Promotion> promotions = promotionRepository.findAll();
            logger.info("Found {} promotions in database", promotions.size());

            // Log each promotion for debugging
            for (Promotion p : promotions) {
                logger.info("Promotion - ID: {}, Title: {}, Active: {}",
                        p.getId(), p.getTitle(), p.getIsActive());
            }

            return promotions;
        } catch (Exception e) {
            logger.error("Error fetching all promotions: ", e);
            throw new RuntimeException("Failed to fetch promotions: " + e.getMessage());
        }
    }

    public List<Promotion> getAllActivePromotions() {
        logger.info("Fetching all active promotions");
        try {
            List<Promotion> promotions = promotionRepository.findByIsActiveTrue();
            logger.info("Found {} active promotions", promotions.size());
            return promotions;
        } catch (Exception e) {
            logger.error("Error fetching active promotions: ", e);
            throw new RuntimeException("Failed to fetch active promotions: " + e.getMessage());
        }
    }

    public List<Promotion> getValidPromotions() {
        logger.info("Fetching valid promotions (active and not expired)");
        try {
            List<Promotion> promotions = promotionRepository.findByEndDateAfterAndIsActiveTrue(LocalDateTime.now());
            logger.info("Found {} valid promotions", promotions.size());
            return promotions;
        } catch (Exception e) {
            logger.error("Error fetching valid promotions: ", e);
            throw new RuntimeException("Failed to fetch valid promotions: " + e.getMessage());
        }
    }

    public Promotion updatePromotion(Long id, Promotion promotionDetails) {
        logger.info("Updating promotion with ID: {}", id);

        try {
            Promotion promotion = promotionRepository.findById(id)
                    .orElseThrow(() -> {
                        logger.error("Promotion not found with ID: {}", id);
                        return new RuntimeException("Promotion not found with ID: " + id);
                    });

            logger.info("Current promotion: Title={}, Type={}, Amount={}",
                    promotion.getTitle(), promotion.getDiscountType(), promotion.getDiscountAmount());

            // Update fields
            if (promotionDetails.getTitle() != null) {
                promotion.setTitle(promotionDetails.getTitle());
            }
            if (promotionDetails.getDescription() != null) {
                promotion.setDescription(promotionDetails.getDescription());
            }
            if (promotionDetails.getDiscountAmount() != null) {
                promotion.setDiscountAmount(promotionDetails.getDiscountAmount());
            }
            if (promotionDetails.getDiscountType() != null) {
                promotion.setDiscountType(promotionDetails.getDiscountType());
            }
            if (promotionDetails.getEndDate() != null) {
                promotion.setEndDate(promotionDetails.getEndDate());
            }
            if (promotionDetails.getUsageLimit() != null) {
                promotion.setUsageLimit(promotionDetails.getUsageLimit());
            }
            if (promotionDetails.getIsActive() != null) {
                promotion.setIsActive(promotionDetails.getIsActive());
            }

            Promotion updatedPromotion = promotionRepository.save(promotion);
            logger.info("Promotion updated successfully: ID={}", updatedPromotion.getId());
            return updatedPromotion;

        } catch (Exception e) {
            logger.error("Error updating promotion with ID {}: ", id, e);
            throw new RuntimeException("Failed to update promotion: " + e.getMessage());
        }
    }

    public void deletePromotion(Long id) {
        logger.info("Soft deleting promotion with ID: {}", id);

        try {
            Promotion promotion = promotionRepository.findById(id)
                    .orElseThrow(() -> {
                        logger.error("Promotion not found for deletion with ID: {}", id);
                        return new RuntimeException("Promotion not found");
                    });

            promotion.setIsActive(false);
            promotionRepository.save(promotion);
            logger.info("Promotion soft deleted successfully: ID={}", id);

        } catch (Exception e) {
            logger.error("Error deleting promotion with ID {}: ", id, e);
            throw new RuntimeException("Failed to delete promotion: " + e.getMessage());
        }
    }

    public boolean validatePromotion(String promotionCode) {
        logger.info("Validating promotion code: {}", promotionCode);

        try {
            Optional<Promotion> promotion = promotionRepository.findByPromotionCode(promotionCode);
            if (promotion.isPresent()) {
                Promotion promo = promotion.get();
                boolean isValid = promo.getIsActive() &&
                        (promo.getUsageLimit() == null || promo.getUsedCount() < promo.getUsageLimit()) &&
                        (promo.getEndDate() == null || promo.getEndDate().isAfter(LocalDateTime.now()));

                logger.info("Promotion code {} validation result: {}", promotionCode, isValid);
                return isValid;
            }
            logger.warn("Promotion code not found: {}", promotionCode);
            return false;
        } catch (Exception e) {
            logger.error("Error validating promotion code {}: ", promotionCode, e);
            return false;
        }
    }

    public Promotion getPromotionById(Long id) {
        logger.info("Fetching promotion by ID: {}", id);

        try {
            Promotion promotion = promotionRepository.findById(id)
                    .orElseThrow(() -> {
                        logger.error("Promotion not found with ID: {}", id);
                        return new RuntimeException("Promotion not found with ID: " + id);
                    });
            logger.info("Found promotion: ID={}, Title={}", promotion.getId(), promotion.getTitle());
            return promotion;
        } catch (Exception e) {
            logger.error("Error fetching promotion by ID {}: ", id, e);
            throw new RuntimeException("Failed to fetch promotion: " + e.getMessage());
        }
    }

    public Promotion getPromotionByCode(String promotionCode) {
        logger.info("Fetching promotion by code: {}", promotionCode);

        try {
            Optional<Promotion> promotion = promotionRepository.findByPromotionCode(promotionCode);
            if (promotion.isPresent()) {
                logger.info("Found promotion by code: {}", promotionCode);
                return promotion.get();
            } else {
                logger.warn("Promotion not found with code: {}", promotionCode);
                throw new RuntimeException("Promotion not found with code: " + promotionCode);
            }
        } catch (Exception e) {
            logger.error("Error fetching promotion by code {}: ", promotionCode, e);
            throw new RuntimeException("Failed to fetch promotion by code: " + e.getMessage());
        }
    }

    public Promotion incrementUsage(String promotionCode) {
        logger.info("Incrementing usage for promotion code: {}", promotionCode);

        try {
            Optional<Promotion> promotionOpt = promotionRepository.findByPromotionCode(promotionCode);
            if (promotionOpt.isPresent()) {
                Promotion promotion = promotionOpt.get();
                int currentCount = promotion.getUsedCount() != null ? promotion.getUsedCount() : 0;
                promotion.setUsedCount(currentCount + 1);

                Promotion updatedPromotion = promotionRepository.save(promotion);
                logger.info("Promotion usage incremented: Code={}, New count={}",
                        promotionCode, updatedPromotion.getUsedCount());
                return updatedPromotion;
            } else {
                logger.warn("Promotion not found for usage increment: {}", promotionCode);
                throw new RuntimeException("Promotion not found with code: " + promotionCode);
            }
        } catch (Exception e) {
            logger.error("Error incrementing usage for promotion code {}: ", promotionCode, e);
            throw new RuntimeException("Failed to increment promotion usage: " + e.getMessage());
        }
    }

    public List<Promotion> getExpiredPromotions() {
        logger.info("Fetching expired promotions");

        try {
            List<Promotion> allPromotions = promotionRepository.findAll();
            List<Promotion> expiredPromotions = allPromotions.stream()
                    .filter(p -> p.getEndDate() != null && p.getEndDate().isBefore(LocalDateTime.now()))
                    .toList();

            logger.info("Found {} expired promotions", expiredPromotions.size());
            return expiredPromotions;
        } catch (Exception e) {
            logger.error("Error fetching expired promotions: ", e);
            throw new RuntimeException("Failed to fetch expired promotions: " + e.getMessage());
        }
    }

    public List<Promotion> getUpcomingPromotions() {
        logger.info("Fetching upcoming promotions");

        try {
            List<Promotion> allPromotions = promotionRepository.findAll();
            List<Promotion> upcomingPromotions = allPromotions.stream()
                    .filter(p -> p.getStartDate() != null && p.getStartDate().isAfter(LocalDateTime.now()))
                    .toList();

            logger.info("Found {} upcoming promotions", upcomingPromotions.size());
            return upcomingPromotions;
        } catch (Exception e) {
            logger.error("Error fetching upcoming promotions: ", e);
            throw new RuntimeException("Failed to fetch upcoming promotions: " + e.getMessage());
        }
    }

    public long getTotalPromotionCount() {
        logger.info("Fetching total promotion count");

        try {
            long count = promotionRepository.count();
            logger.info("Total promotions count: {}", count);
            return count;
        } catch (Exception e) {
            logger.error("Error fetching total promotion count: ", e);
            throw new RuntimeException("Failed to fetch promotion count: " + e.getMessage());
        }
    }

    public long getActivePromotionCount() {
        logger.info("Fetching active promotion count");

        try {
            List<Promotion> activePromotions = promotionRepository.findByIsActiveTrue();
            long count = activePromotions.size();
            logger.info("Active promotions count: {}", count);
            return count;
        } catch (Exception e) {
            logger.error("Error fetching active promotion count: ", e);
            throw new RuntimeException("Failed to fetch active promotion count: " + e.getMessage());
        }
    }
}