package com.lucasrznd.apigspot.repositories;

import com.lucasrznd.apigspot.models.TopCompanyOfTheMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopCompanyRepository extends JpaRepository<TopCompanyOfTheMonth, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM top_monthly_company")
    List<TopCompanyOfTheMonth> findTopCompaniesOfTheMonth();

}
