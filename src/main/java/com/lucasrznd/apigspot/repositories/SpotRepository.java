package com.lucasrznd.apigspot.repositories;

import com.lucasrznd.apigspot.models.SpotModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotRepository extends JpaRepository<SpotModel, Long> {
}
