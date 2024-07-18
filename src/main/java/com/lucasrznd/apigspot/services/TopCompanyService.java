package com.lucasrznd.apigspot.services;

import com.lucasrznd.apigspot.dtos.response.TopCompanyResponse;
import com.lucasrznd.apigspot.dtos.mappers.TopCompanyMapper;
import com.lucasrznd.apigspot.repositories.TopCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopCompanyService {

    private final TopCompanyRepository topCompanyRepository;
    private final TopCompanyMapper mapper;

    public List<TopCompanyResponse> findTopCompaniesOnCurrentMonth() {
        return topCompanyRepository.findTopCompaniesOnCurrentMonth()
                .stream().map(mapper::toDTO).toList();
    }

}
