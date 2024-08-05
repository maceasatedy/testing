package com.profile.matcher.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class Inventory {

    private long cash;
    private long coins;

    @ElementCollection
    private Map<String, Integer> items;
}
