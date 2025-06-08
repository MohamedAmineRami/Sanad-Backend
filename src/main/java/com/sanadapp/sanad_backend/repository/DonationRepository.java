package com.sanadapp.sanad_backend.repository;

import com.sanadapp.sanad_backend.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, String> {
    List<Donation> findByUserId(String userId);
    List<Donation> findByCampaignId(Long campaign_id);
}
