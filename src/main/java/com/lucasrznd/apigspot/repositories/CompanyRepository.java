package com.lucasrznd.apigspot.repositories;

import com.lucasrznd.apigspot.models.CompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyModel, Long> {

    CompanyModel findByName(String name);

}
