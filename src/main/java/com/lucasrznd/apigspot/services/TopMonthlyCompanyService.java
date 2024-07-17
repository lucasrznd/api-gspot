package com.lucasrznd.apigspot.services;

import com.lucasrznd.apigspot.dtos.request.TopMonthlyCompanyDTO;
import com.lucasrznd.apigspot.dtos.mappers.TopMonthlyCompanyMapper;
import com.lucasrznd.apigspot.repositories.TopMonthlyCompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopMonthlyCompanyService {

    private final TopMonthlyCompanyRepository topMonthlyCompanyRepository;
    private final TopMonthlyCompanyMapper mapper;

    public TopMonthlyCompanyService(TopMonthlyCompanyRepository topMonthlyCompanyRepository, TopMonthlyCompanyMapper mapper) {
        this.topMonthlyCompanyRepository = topMonthlyCompanyRepository;
        this.mapper = mapper;
    }

    public List<TopMonthlyCompanyDTO> findTopMonthlyCompanies() {
        return topMonthlyCompanyRepository.findTopMonthlyCompanies()
                .stream().map(mapper::toDTO).toList();
    }

}
