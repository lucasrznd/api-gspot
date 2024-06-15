package com.lucasrznd.apigspot.repositories;

import com.lucasrznd.apigspot.models.TopMonthlyAnnouncer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopMonthlyAnnouncerRepository extends JpaRepository<TopMonthlyAnnouncer, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM top_monthly_announcer")
    List<TopMonthlyAnnouncer> findTopMonthlyAnnouncers();

}
