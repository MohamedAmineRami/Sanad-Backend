package com.sanadapp.sanad_backend.dto;

import com.sanadapp.sanad_backend.entity.Campaign;
import lombok.*;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampaignDTO {
    private Long id;
    private String title;
    private String description;
    private String category;
    private Double goal;
    private Double raised;
    private Integer progress;
    private Integer participants;
    private String imageUrl;
    private String status;
    private boolean verified;
    private Instant createdAt;
    private Instant updatedAt;

    // Only include organization info we need, without circular reference
    private OrganizationBasicDTO organization;

    // Static factory method to create DTO from entity
    public static CampaignDTO from(Campaign campaign) {
        if (campaign == null) {
            return null;
        }

        return CampaignDTO.builder()
                .id(campaign.getId())
                .title(campaign.getTitle())
                .description(campaign.getDescription())
                .category(campaign.getCategory())
                .goal(campaign.getGoal())
                .raised(campaign.getRaised())
                .progress(campaign.getProgress())
                .participants(campaign.getParticipants())
                .imageUrl(campaign.getImageUrl())
                .status(campaign.getStatus())
                .verified(campaign.isVerified())
                .createdAt(campaign.getCreatedAt())
                .updatedAt(campaign.getUpdatedAt())
                .organization(OrganizationBasicDTO.from(campaign.getOrganization()))
                .build();
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class OrganizationBasicDTO {
    private String id;
    private String name;
    private String description;
    private String email;
    private String website;
    private boolean verified;
    private String logoUrl;
    private Instant createdAt;

    // Static factory method to create DTO from entity
    public static OrganizationBasicDTO from(com.sanadapp.sanad_backend.entity.Organization organization) {
        if (organization == null) {
            return null;
        }

        return OrganizationBasicDTO.builder()
                .id(organization.getId())
                .name(organization.getName())
                .description(organization.getDescription())
                .email(organization.getEmail())
                .website(organization.getWebsite())
                .verified(organization.isVerified())
                .logoUrl(organization.getLogoUrl())
                .createdAt(organization.getCreatedAt())
                .build();
    }
}