package com.profile.matcher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "carrier")
    private String carrier;

    @Column(name = "firmware")
    private String firmware;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playerId")
    @JsonIgnore
    private Player player;
}
