package com.lucasrznd.apigspot.repositories;

import com.lucasrznd.apigspot.models.TopMonthlyCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopMonthlyCompanyRepository extends JpaRepository<TopMonthlyCompany, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM top_monthly_company")
    List<TopMonthlyCompany> findTopMonthlyCompanies();

}
