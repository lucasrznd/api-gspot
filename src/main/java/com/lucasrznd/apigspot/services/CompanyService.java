package com.lucasrznd.apigspot.services;

import com.lucasrznd.apigspot.dtos.CompanyDTO;
import com.lucasrznd.apigspot.dtos.mappers.CompanyMapper;
import com.lucasrznd.apigspot.exceptions.company.CompanyNotFoundException;
import com.lucasrznd.apigspot.exceptions.company.DuplicateCompanyException;
import com.lucasrznd.apigspot.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    public List<CompanyDTO> selectAll() {
        return companyRepository.findAll().stream().map(companyMapper::toDTO).toList();
    }

    public Long countCompanies() {
        return companyRepository.count();
    }

    public CompanyDTO insert(CompanyDTO companyDTO) {
        CompanyDTO companyFounded = companyMapper.toDTO(companyRepository.findByName(companyDTO.name()));

        if (companyFounded != null) {
            throw new DuplicateCompanyException("Company already exists.");
        }

        return companyMapper.toDTO(companyRepository.save(companyMapper.toModel(companyDTO)));
    }

    public CompanyDTO update(Long id, CompanyDTO companyDTO) {
        return companyRepository.findById(id)
                .map(companyFound -> {
                    companyFound.setName(companyDTO.name());
                    companyFound.setPhoneNumber(companyDTO.phoneNumber());
                    companyFound.setUrlImage(companyDTO.urlImage());
                    return companyMapper.toDTO(companyRepository.save(companyFound));
                }).orElseThrow(() -> new CompanyNotFoundException("Announcer not found."));
    }

    public void delete(Long id) {
        companyRepository.delete(companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException("Company not found.")));
    }

}
