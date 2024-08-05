package com.profile.matcher.controller;

import com.profile.matcher.model.PlayerProfile;
import com.profile.matcher.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @GetMapping("/all")
    public ResponseEntity<List<PlayerProfile>> getAllProfiles() {
        List<PlayerProfile> profiles = profileService.getAllProfiles();
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerProfile> getClientProfileById(@PathVariable Long player_id) {
        PlayerProfile profile = profileService.getPlayerProfileById(player_id);
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/get_client_config/{player_id}")
    public ResponseEntity<PlayerProfile> getClientConfig(@PathVariable("player_id") Long player_id) {
        PlayerProfile updatedProfile = profileService.updatePlayerProfile(player_id);
        return ResponseEntity.ok(updatedProfile);
    }
}
