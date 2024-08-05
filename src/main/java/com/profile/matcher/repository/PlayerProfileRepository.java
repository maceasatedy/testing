package com.profile.matcher.repository;

import com.profile.matcher.model.PlayerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlayerProfileRepository extends JpaRepository<PlayerProfile, Long>  {


}
