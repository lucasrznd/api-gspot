package com.lucasrznd.apigspot.repositories;

import com.lucasrznd.apigspot.models.SpotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SpotRepository extends JpaRepository<SpotModel, Long> {

    @Query("SELECT SUM(s.price) FROM SpotModel s")
    Double getAmountRaised();

    @Query(nativeQuery = true, value = "SELECT * FROM amount_raised_month")
    Double getAmountRaisedMonth();

    @Query("SELECT s FROM SpotModel s ORDER BY s.date DESC LIMIT 5")
    List<SpotModel> findLatestSpots();

    @Query(value = "SELECT s FROM SpotModel s "
            + "WHERE s.date BETWEEN :initialDate AND :finalDate "
            + "AND (COALESCE(:companyName, '') = '' OR s.company.name LIKE :companyName) "
            + "AND (COALESCE(:announcerName, '') = '' OR s.announcer.name LIKE :announcerName) "
            + "ORDER BY s.date ASC")
    List<SpotModel> findByDateRangeAnnouncerAndCompany(LocalDate initialDate, LocalDate finalDate, String companyName, String announcerName);

}
