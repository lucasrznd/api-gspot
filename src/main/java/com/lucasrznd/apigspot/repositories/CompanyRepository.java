package com.lucasrznd.apigspot.repositories;

import com.lucasrznd.apigspot.models.CompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<CompanyModel, Long> {

    Optional<CompanyModel> findByName(String name);
    Optional<CompanyModel> findByPhoneNumber(String phoneNumber);

}
