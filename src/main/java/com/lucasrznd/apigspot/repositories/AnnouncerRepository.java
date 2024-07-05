package com.lucasrznd.apigspot.repositories;

import com.lucasrznd.apigspot.models.AnnouncerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnnouncerRepository extends JpaRepository<AnnouncerModel, Long> {

    Optional<AnnouncerModel> findByName(String name);

    Optional<AnnouncerModel> findByPhoneNumber(String phoneNumber);

}
