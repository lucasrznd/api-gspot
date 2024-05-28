package com.lucasrznd.apigspot.repositories;

import com.lucasrznd.apigspot.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
