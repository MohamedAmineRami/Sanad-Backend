package com.sanadapp.sanad_backend.controller;

import com.sanadapp.sanad_backend.dto.CampaignDTO;
import com.sanadapp.sanad_backend.service.CampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/campaigns")
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;

    @GetMapping
    public List<CampaignDTO> getAllActive(@RequestParam(required = false) String category) {
        if (category != null && !category.isEmpty()) {
            return campaignService.getActiveCampaignsByCategoryDTO(category);
        }
        return campaignService.getAllActiveCampaignsDTO();
    }

    @GetMapping("/{id}")
    public CampaignDTO getById(@PathVariable Long id) {
        return campaignService.getCampaignByIdDTO(id);
    }
}