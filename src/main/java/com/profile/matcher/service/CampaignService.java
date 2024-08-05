package com.profile.matcher.service;

import com.profile.matcher.model.Campaign;
import com.profile.matcher.repository.CampaignRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CampaignService {


    private final CampaignRepository campaignRepository;

    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public List<Campaign> getCurrentCampaigns() {
        return campaignRepository.findActiveCampaigns(LocalDateTime.now());
    }
}
