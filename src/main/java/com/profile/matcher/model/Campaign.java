package com.profile.matcher.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@Getter
@Setter
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "game")
    private String game;

    @Column(name = "name")
    private String name;

    @Column(name = "priority")
    private double priority;

    @Embedded
    private Matcher matcher;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

}
