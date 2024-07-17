package com.lucasrznd.apigspot.dtos.mappers;

import com.lucasrznd.apigspot.dtos.request.TopMonthlyCompanyDTO;
import com.lucasrznd.apigspot.models.TopMonthlyCompany;
import org.springframework.stereotype.Component;

@Component
public class TopMonthlyCompanyMapper {

    public TopMonthlyCompanyDTO toDTO(TopMonthlyCompany topMonthlyCompany) {
        if (topMonthlyCompany == null) {
            return null;
        }

        return new TopMonthlyCompanyDTO(topMonthlyCompany.getCompanyName(), topMonthlyCompany.getSpotCount());
    }

    public TopMonthlyCompany toModel(TopMonthlyCompanyDTO topMonthlyCompanyDTO) {
        if (topMonthlyCompanyDTO == null) {
            return null;
        }

        TopMonthlyCompany topMonthlyCompany = new TopMonthlyCompany();
        topMonthlyCompany.setCompanyName(topMonthlyCompanyDTO.companyName());
        topMonthlyCompany.setSpotCount(topMonthlyCompanyDTO.spotCount());

        return topMonthlyCompany;
    }

}
