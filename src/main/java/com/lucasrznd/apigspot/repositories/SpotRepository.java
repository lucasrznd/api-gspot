package com.lucasrznd.apigspot.repositories;

import com.lucasrznd.apigspot.models.SpotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface SpotRepository extends JpaRepository<SpotModel, Long> {

    @Query("SELECT SUM(s.price) FROM SpotModel s")
    BigDecimal getAmountRaised();

}
