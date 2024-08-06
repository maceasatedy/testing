package com.profile.matcher.service;

import com.profile.matcher.model.Campaign;
import com.profile.matcher.model.Inventory;
import com.profile.matcher.model.Matcher;
import com.profile.matcher.model.Player;
import com.profile.matcher.repository.CampaignRepository;
import com.profile.matcher.repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PlayerService {

    @Autowired
    private final PlayerRepository playerRepository;

    @Autowired
    private final CampaignService campaignService;


    public PlayerService(PlayerRepository playerRepository, CampaignService campaignService) {
        this.playerRepository = playerRepository;
        this.campaignService = campaignService;
    }

    public List<Player> getAllProfiles(){
        for(int i=0; i<3; i++){
            System.out.println(i);
        }
        List<Player> profiles = (List<Player>) playerRepository.findAll();
        if (profiles.isEmpty()){
            log.info("List of profiles is empty.");
        }
        return profiles;

    }

    public Player getPlayerById(Long playerId) {
        Optional<Player> profile = playerRepository.findById(playerId);
        if (profile.isPresent()){
            log.info("Found player: {}", profile);
            return profile.get();
        }
        log.info("Player not found for id: "+playerId);
        throw new ResourceNotFoundException("Player not found.");
    }

    public Player updatePlayerProfile(Long playerId){
        log.info("Starting updating player profile for playerId:{}", playerId);

        Player profile = getPlayerById(playerId);


        List<Campaign> campaigns = campaignService.getCurrentCampaigns();

        campaigns.stream()
                .filter(campaign -> matchesCampaign(profile, campaign))
                .forEach(profile.getActiveCampaigns()::add);

        return playerRepository.save(profile);
    }

    private boolean matchesCampaign(Player profile, Campaign campaign){

        Matcher matchers = campaign.getMatcher();
        Inventory inventory = profile.getInventory();

        return profile.getLevel() >= matchers.getLevel().getMin() && profile.getLevel() <= matchers.getLevel().getMax()
                && matchers.getHas().getCountries().contains(profile.getCountry())
                && inventory.getItems().keySet().containsAll(matchers.getHas().getItems())
                && Collections.disjoint(inventory.getItems().keySet(), matchers.getDoesNotHave().getItems());

    }

}
