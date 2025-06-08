package com.sanadapp.sanad_backend.repository;

import com.sanadapp.sanad_backend.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findByStatus(String status);
    List<Campaign> findByVerified(boolean verified);
    List<Campaign> findByStatusAndCategory(String status, String category);
    List<Campaign> findByCategory(String category);
}