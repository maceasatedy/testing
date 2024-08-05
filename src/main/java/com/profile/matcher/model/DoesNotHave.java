package com.profile.matcher.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Embeddable
@AllArgsConstructor
@Getter
@Setter
public class DoesNotHave {

    @ElementCollection
    private List<String> items;
}
