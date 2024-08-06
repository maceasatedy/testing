package com.profile.matcher.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Inventory {

    private int cash;
    private int coins;

    @ElementCollection
    private Map<String, Integer> items;
}
