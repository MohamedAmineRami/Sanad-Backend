package com.sanadapp.sanad_backend.service;

import com.sanadapp.sanad_backend.dto.DonationRequest;
import com.sanadapp.sanad_backend.dto.DonationResponse;
import com.sanadapp.sanad_backend.entity.Activity;
import com.sanadapp.sanad_backend.entity.Campaign;
import com.sanadapp.sanad_backend.entity.Donation;
import com.sanadapp.sanad_backend.entity.User;
import com.sanadapp.sanad_backend.repository.ActivityRepository;
import com.sanadapp.sanad_backend.repository.CampaignRepository;
import com.sanadapp.sanad_backend.repository.DonationRepository;
import com.sanadapp.sanad_backend.repository.UserRepository;
import com.sanadapp.sanad_backend.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;
    private final UserRepository userRepository;
    private final CampaignRepository campaignRepository;
    private final ActivityRepository activityRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public List<DonationResponse> getDonationsByUserId(String userId) {
        List<Donation> donations = donationRepository.findByUserId(userId);
        return donations.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public List<DonationResponse> getDonationsByCampaignId(Long campaignId) {
        List<Donation> donations = donationRepository.findByCampaignId(campaignId);
        return donations.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public DonationResponse getDonationById(String donationId) {
        Donation donation = donationRepository.findById(donationId)
                .orElseThrow(() -> new RuntimeException("Donation not found"));
        return convertToResponse(donation);
    }

    @Transactional
    public DonationResponse createDonation(DonationRequest request, String token) {
        // Extract user ID from JWT token
        String userId = jwtTokenProvider.getUserIdFromToken(token);

        // Fetch user and campaign
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Campaign campaign = campaignRepository.findById(request.getCampaignId())
                .orElseThrow(() -> new RuntimeException("Campaign not found"));

        // Create donation entity
        Donation donation = Donation.builder()
                .id(UUID.randomUUID().toString())
                .amount(request.getAmount())
                .currency(request.getCurrency() != null ? request.getCurrency() : "EUR")
                .status("PENDING") // Initial status
                .paymentMethod(request.getPaymentMethod())
                .anonymous(request.isAnonymous())
                .createdAt(Instant.now())
                .user(user)
                .campaign(campaign)
                .build();

        // Save donation
        donation = donationRepository.save(donation);

        // Update user's donation statistics
        user.setTotalDonated((user.getTotalDonated() != null ? user.getTotalDonated() : 0.0) + request.getAmount());
        user.setDonationsCount((user.getDonationsCount() != null ? user.getDonationsCount() : 0) + 1);
        userRepository.save(user);

        // Create activity for the donation (only if not anonymous)
        if (!request.isAnonymous()) {
            createDonationActivity(user, campaign, request.getAmount());
        }

        return convertToResponse(donation);
    }

    @Transactional
    public DonationResponse completeDonation(String donationId) {
        Donation donation = donationRepository.findById(donationId)
                .orElseThrow(() -> new RuntimeException("Donation not found"));

        donation.setStatus("COMPLETED");
        donation.setCompletedAt(Instant.now());

        donation = donationRepository.save(donation);
        return convertToResponse(donation);
    }

    private void createDonationActivity(User user, Campaign campaign, Double amount) {
        String message = String.format("%s donó %.2f€ a \"%s\"",
                user.getName(),
                amount,
                campaign.getTitle().length() > 30 ?
                        campaign.getTitle().substring(0, 27) + "..." :
                        campaign.getTitle()
        );

        Activity activity = Activity.builder()
                .id(UUID.randomUUID().toString())
                .type("donation")
                .message(message)
                .createdAt(Instant.now())
                .isPublic(true)
                .user(user)
                .campaign(campaign)
                .build();

        activityRepository.save(activity);
    }

    private DonationResponse convertToResponse(Donation donation) {
        return DonationResponse.builder()
                .id(donation.getId())
                .amount(donation.getAmount())
                .currency(donation.getCurrency())
                .status(donation.getStatus())
                .paymentMethod(donation.getPaymentMethod())
                .anonymous(donation.isAnonymous())
                .createdAt(donation.getCreatedAt())
                .completedAt(donation.getCompletedAt())
                .userId(donation.isAnonymous() ? null : donation.getUser().getId())
                .userName(donation.isAnonymous() ? null : donation.getUser().getName())
                .campaignId(donation.getCampaign().getId())
                .campaignTitle(donation.getCampaign().getTitle())
                .build();
    }
}