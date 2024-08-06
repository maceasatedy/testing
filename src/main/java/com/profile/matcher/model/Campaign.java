package com.profile.matcher.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "matcher_id")
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
