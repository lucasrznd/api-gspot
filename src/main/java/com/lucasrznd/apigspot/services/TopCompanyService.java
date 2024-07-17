package com.lucasrznd.apigspot.services;

import com.lucasrznd.apigspot.dtos.response.TopCompanyResponse;
import com.lucasrznd.apigspot.dtos.mappers.TopCompanyMapper;
import com.lucasrznd.apigspot.repositories.TopCompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopCompanyService {

    private final TopCompanyRepository topCompanyRepository;
    private final TopCompanyMapper mapper;

    public TopCompanyService(TopCompanyRepository topCompanyRepository, TopCompanyMapper mapper) {
        this.topCompanyRepository = topCompanyRepository;
        this.mapper = mapper;
    }

    public List<TopCompanyResponse> findTopMonthlyCompanies() {
        return topCompanyRepository.findTopCompaniesOfTheMonth()
                .stream().map(mapper::toDTO).toList();
    }

}
