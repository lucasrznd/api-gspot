package com.lucasrznd.apigspot.repositories;

import com.lucasrznd.apigspot.models.TopAnnouncerOfTheMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopAnnouncerRepository extends JpaRepository<TopAnnouncerOfTheMonth, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM top_monthly_announcer")
    List<TopAnnouncerOfTheMonth> findTopAnnouncersOfTheMonth();

}
