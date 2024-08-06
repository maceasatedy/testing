package com.profile.matcher.controller;

import com.profile.matcher.model.Player;
import com.profile.matcher.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @GetMapping("/all")
    public ResponseEntity<List<Player>> getAllProfiles() {
        List<Player> profiles = playerService.getAllProfiles();
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable("id") Long id) {
        Player profile = playerService.getPlayerById(id);
        return ResponseEntity.ok(profile);
    }

    @GetMapping("/get_client_config/{id}")
    public ResponseEntity<Player> getClientConfig(@PathVariable("id") Long id) {
        Player updatedProfile = playerService.updatePlayerProfile(id);
        return ResponseEntity.ok(updatedProfile);
    }
}
