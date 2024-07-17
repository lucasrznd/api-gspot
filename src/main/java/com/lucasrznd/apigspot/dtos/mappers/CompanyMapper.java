package com.lucasrznd.apigspot.dtos.mappers;

import com.lucasrznd.apigspot.dtos.request.CompanyDTO;
import com.lucasrznd.apigspot.models.CompanyModel;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public CompanyDTO toDTO(CompanyModel company) {
        if (company == null) {
            return null;
        }

        return new CompanyDTO(company.getId(), company.getName(), company.getPhoneNumber(), company.getUrlImage());
    }

    public CompanyModel toModel(CompanyDTO companyDTO) {
        if (companyDTO == null) {
            return null;
        }

        CompanyModel companyModel = new CompanyModel();
        companyModel.setId(companyDTO.id());
        companyModel.setName(companyDTO.name());
        companyModel.setPhoneNumber(companyDTO.phoneNumber());
        companyModel.setUrlImage(companyDTO.urlImage());

        return companyModel;
    }

}
