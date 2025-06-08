package com.sanadapp.sanad_backend.controller;

import com.sanadapp.sanad_backend.dto.DonationRequest;
import com.sanadapp.sanad_backend.dto.DonationResponse;
import com.sanadapp.sanad_backend.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donations")
@RequiredArgsConstructor
public class DonationController {

    private final DonationService donationService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DonationResponse>> getByUser(@PathVariable String userId) {
        List<DonationResponse> donations = donationService.getDonationsByUserId(userId);
        return ResponseEntity.ok(donations);
    }

    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<DonationResponse>> getByCampaign(@PathVariable Long campaignId) {
        List<DonationResponse> donations = donationService.getDonationsByCampaignId(campaignId);
        return ResponseEntity.ok(donations);
    }

    @PostMapping
    public ResponseEntity<DonationResponse> createDonation(
            @RequestBody DonationRequest request,
            @RequestHeader("Authorization") String authorization) {

        // Extract token from Authorization header
        String token = authorization.replace("Bearer ", "");

        DonationResponse donation = donationService.createDonation(request, token);
        return ResponseEntity.status(HttpStatus.CREATED).body(donation);
    }

    @GetMapping("/{donationId}")
    public ResponseEntity<DonationResponse> getDonationById(@PathVariable String donationId) {
        DonationResponse donation = donationService.getDonationById(donationId);
        return ResponseEntity.ok(donation);
    }
}