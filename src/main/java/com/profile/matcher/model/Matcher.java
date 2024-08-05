package com.profile.matcher.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class Matcher {

    @Embedded
    private Level level;

    @Embedded
    private Has has;

    @Embedded
    private DoesNotHave doesNotHave;
}
