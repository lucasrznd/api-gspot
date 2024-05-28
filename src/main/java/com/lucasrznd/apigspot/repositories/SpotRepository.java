package com.lucasrznd.apigspot.repositories;

import com.lucasrznd.apigspot.models.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotRepository extends JpaRepository<Spot, Long> {
}
