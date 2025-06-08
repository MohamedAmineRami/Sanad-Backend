package com.sanadapp.sanad_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonationRequest {
    private Double amount;
    private String currency;
    private Long campaignId;
    private String paymentMethod;
    private boolean anonymous;
}