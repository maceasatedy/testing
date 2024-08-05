package com.profile.matcher.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerProfileId;

    @Column(name = "credential")
    private String credential;

    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "modified")
    private LocalDateTime modified;

    @Column(name = "last_session")
    private LocalDateTime lastSession;

    @Column(name = "total_spent")
    private long totalSpent;

    @Column(name = "total_refund")
    private long totalRefund;

    @Column(name = "total_transactions")
    private long totalTransactions;

    @Column(name = "last_purchase")
    private LocalDateTime lastPurchase;

    @ElementCollection
    private List<Campaign> activeCampaigns;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "playerProfile")
    private List<Device> devices;

    @Column(name = "level")
    private long level;

    @Column(name = "xp")
    private long xp;

    @Column(name = "total_play_time")
    private long totalPlaytime;

    @Column(name = "country")
    private String country;

    @Column(name = "language")
    private String language;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "gender")
    private String gender;

    @Embedded
    private Inventory inventory;

    @Embedded
    private Clan clan;

    @Column(name = "custom_field")
    private String customField;
}
