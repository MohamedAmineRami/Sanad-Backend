package com.sanadapp.sanad_backend.service;

import com.sanadapp.sanad_backend.dto.CampaignDTO;
import com.sanadapp.sanad_backend.entity.Campaign;
import com.sanadapp.sanad_backend.repository.CampaignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampaignService {

    private final CampaignRepository campaignRepository;

    public List<Campaign> getAllActiveCampaigns() {
        return campaignRepository.findByStatus("active");
    }

    public List<CampaignDTO> getAllActiveCampaignsDTO() {
        return campaignRepository.findByStatus("active")
                .stream()
                .map(CampaignDTO::from)
                .collect(Collectors.toList());
    }

    public Campaign getCampaignById(Long id) {
        return campaignRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campaign not found"));
    }

    public CampaignDTO getCampaignByIdDTO(Long id) {
        Campaign campaign = getCampaignById(id);
        return CampaignDTO.from(campaign);
    }

    // New methods for category filtering
    public List<Campaign> getActiveCampaignsByCategory(String category) {
        return campaignRepository.findByStatusAndCategory("active", category);
    }

    public List<CampaignDTO> getActiveCampaignsByCategoryDTO(String category) {
        return campaignRepository.findByStatusAndCategory("active", category)
                .stream()
                .map(CampaignDTO::from)
                .collect(Collectors.toList());
    }
}