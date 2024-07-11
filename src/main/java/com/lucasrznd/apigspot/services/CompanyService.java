package com.lucasrznd.apigspot.services;

import com.lucasrznd.apigspot.dtos.CompanyDTO;
import com.lucasrznd.apigspot.dtos.mappers.CompanyMapper;
import com.lucasrznd.apigspot.exceptions.common.NameAlreadyExistsException;
import com.lucasrznd.apigspot.exceptions.common.PhoneNumberAlreadyExistsException;
import com.lucasrznd.apigspot.exceptions.company.CompanyNotFoundException;
import com.lucasrznd.apigspot.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        checkIfNameAlreadyExists(companyDTO.name());
        checkIfPhoneNumberAlreadyExists(companyDTO.phoneNumber());

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

    public void checkIfNameAlreadyExists(String name) {
        Optional<CompanyDTO> companyFounded = findByName(name);

        if (companyFounded.isPresent()) {
            throw new NameAlreadyExistsException("Name already exists.");
        }
    }

    public void checkIfPhoneNumberAlreadyExists(String phoneNumber) {
        Optional<CompanyDTO> companyFounded = findByPhoneNumber(phoneNumber);

        if (companyFounded.isPresent()) {
            throw new PhoneNumberAlreadyExistsException("Phone Number already exists.");
        }
    }

    public Optional<CompanyDTO> findByName(String name) {
        return companyRepository.findByName(name).isPresent()
                ? Optional.of(companyMapper.toDTO(companyRepository.findByName(name).get()))
                : Optional.empty();
    }

    public Optional<CompanyDTO> findByPhoneNumber(String phoneNumber) {
        return companyRepository.findByPhoneNumber(phoneNumber).isPresent()
                ? Optional.of(companyMapper.toDTO(companyRepository.findByPhoneNumber(phoneNumber).get()))
                : Optional.empty();
    }

}
