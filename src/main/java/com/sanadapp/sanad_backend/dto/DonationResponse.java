package com.sanadapp.sanad_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonationResponse {
    private String id;
    private Double amount;
    private String currency;
    private String status;
    private String paymentMethod;
    private boolean anonymous;
    private Instant createdAt;
    private Instant completedAt;

    // User info (only if not anonymous)
    private String userId;
    private String userName;

    // Campaign info
    private Long campaignId;
    private String campaignTitle;
}