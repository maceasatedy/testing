package com.profile.matcher.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Matcher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matcher_id;

    @Embedded
    private Level level;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "has_id")
    private Has has;

    @Embedded
    private DoesNotHave doesNotHave;
}
