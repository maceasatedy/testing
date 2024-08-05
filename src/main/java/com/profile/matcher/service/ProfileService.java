package com.profile.matcher.service;

import com.profile.matcher.model.Campaign;
import com.profile.matcher.model.Inventory;
import com.profile.matcher.model.Matcher;
import com.profile.matcher.model.PlayerProfile;
import com.profile.matcher.repository.PlayerProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class ProfileService {

    @Autowired
    private final PlayerProfileRepository playerProfileRepository;

    @Autowired
    private final CampaignService campaignService;


    public ProfileService(PlayerProfileRepository playerProfileRepository, CampaignService campaignService) {
        this.playerProfileRepository = playerProfileRepository;
        this.campaignService = campaignService;
    }

    public List<PlayerProfile> getAllProfiles(){
        List<PlayerProfile> profiles = (List<PlayerProfile>) playerProfileRepository.findAll();
        if (profiles.isEmpty()){
            log.info("List of profiles is empty.");
        }
        return profiles;

    }

    public PlayerProfile getPlayerProfileById(Long player_Id) {
        System.out.println("TEST");
        log.info("TEST log");
        PlayerProfile profile = playerProfileRepository.findById(player_Id).orElseThrow(()-> new ResourceNotFoundException("Player not found."));
        if (profile == null) {
            log.error("Error while fetching player profile from database.");
        }
        log.info("Found player: {}", profile);
        return profile;
    }

    public PlayerProfile updatePlayerProfile(Long playerId){
        log.info("Starting updating player profile for playerId:{}", playerId);

        PlayerProfile profile = getPlayerProfileById(playerId);


        List<Campaign> campaigns = campaignService.getCurrentCampaigns();

        campaigns.stream()
                .filter(campaign -> matchesCampaign(profile, campaign))
                .forEach(profile.getActiveCampaigns()::add);

        return playerProfileRepository.save(profile);
    }

    private boolean matchesCampaign(PlayerProfile profile, Campaign campaign){

        Matcher matchers = campaign.getMatcher();
        Inventory inventory = profile.getInventory();

        return profile.getLevel() >= matchers.getLevel().getMin() && profile.getLevel() <= matchers.getLevel().getMax()
                && matchers.getHas().getCountries().contains(profile.getCountry())
                && inventory.getItems().keySet().containsAll(matchers.getHas().getItems())
                && Collections.disjoint(inventory.getItems().keySet(), matchers.getDoesNotHave().getItems());

    }

}
