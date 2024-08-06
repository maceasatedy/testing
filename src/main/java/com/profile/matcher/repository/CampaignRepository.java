package com.profile.matcher.repository;

import com.profile.matcher.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    @Query("SELECT c FROM Campaign c WHERE :currentDate BETWEEN c.startDate AND c.endDate AND c.enabled = true")
    List<Campaign> findActiveCampaigns(@Param("currentDate") LocalDateTime currentDate);

}
