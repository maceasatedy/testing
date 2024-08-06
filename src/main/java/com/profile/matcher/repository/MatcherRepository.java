package com.profile.matcher.repository;

import com.profile.matcher.model.Matcher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatcherRepository extends JpaRepository<Matcher, Long> {
}
