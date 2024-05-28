package com.lucasrznd.apigspot.repositories;

import com.lucasrznd.apigspot.models.AnnouncerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncerRepository extends JpaRepository<AnnouncerModel, Long> {
}
