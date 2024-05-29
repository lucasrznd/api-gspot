package com.lucasrznd.apigspot.repositories;

import com.lucasrznd.apigspot.models.AnnouncerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnnouncerRepository extends JpaRepository<AnnouncerModel, Long> {

    @Query("SELECT a FROM AnnouncerModel a WHERE a.name = ?1 AND a.phoneNumber = ?2")
    AnnouncerModel findByNameAndPhoneNumber(String name, String phoneNumber);

}
