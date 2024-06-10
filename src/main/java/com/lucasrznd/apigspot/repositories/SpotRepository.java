package com.lucasrznd.apigspot.repositories;

import com.lucasrznd.apigspot.models.SpotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface SpotRepository extends JpaRepository<SpotModel, Long> {

    @Query("SELECT SUM(s.price) FROM SpotModel s")
    BigDecimal getAmountRaised();

    @Query("SELECT s FROM SpotModel s ORDER BY s.id DESC LIMIT 5")
    List<SpotModel> getLatestSpots();

    @Query(value = "SELECT s FROM SpotModel s "
            + "WHERE s.date BETWEEN :initialDate AND :finalDate "
            + "AND (COALESCE(:companyName, '') = '' OR s.company.name LIKE :companyName) "
            + "AND (COALESCE(:announcerName, '') = '' OR s.announcer.name LIKE :announcerName) "
            + "ORDER BY s.date ASC")
    List<SpotModel> getByDateRangeAnnouncerAndCompany(LocalDate initialDate, LocalDate finalDate, String companyName, String announcerName);

}
