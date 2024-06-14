package com.lucasrznd.apigspot.services;

import com.lucasrznd.apigspot.models.TopMonthlyCompany;
import com.lucasrznd.apigspot.repositories.TopMonthlyCompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopMonthlyCompanyService {

    private final TopMonthlyCompanyRepository topMonthlyCompanyRepository;

    public TopMonthlyCompanyService(TopMonthlyCompanyRepository topMonthlyCompanyRepository) {
        this.topMonthlyCompanyRepository = topMonthlyCompanyRepository;
    }

    public List<TopMonthlyCompany> findTopMonthlyCompanies() {
        return topMonthlyCompanyRepository.findTopMonthlyCompanies();
    }

}
