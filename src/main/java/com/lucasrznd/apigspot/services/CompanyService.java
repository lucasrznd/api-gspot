package com.lucasrznd.apigspot.services;

import com.lucasrznd.apigspot.dtos.request.CompanyDTO;
import com.lucasrznd.apigspot.dtos.mappers.CompanyMapper;
import com.lucasrznd.apigspot.exceptions.common.NameAlreadyExistsException;
import com.lucasrznd.apigspot.exceptions.common.PhoneNumberAlreadyExistsException;
import com.lucasrznd.apigspot.exceptions.common.ResourceNotFoundException;
import com.lucasrznd.apigspot.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;
    private final CompanyMapper mapper;

    public List<CompanyDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    public Long count() {
        return repository.count();
    }

    public CompanyDTO save(CompanyDTO companyDTO) {
        checkIfNameAlreadyExists(companyDTO.name());
        checkIfPhoneNumberAlreadyExists(companyDTO.phoneNumber());

        return mapper.toDTO(repository.save(mapper.toModel(companyDTO)));
    }

    public CompanyDTO update(Long id, CompanyDTO companyDTO) {
        return repository.findById(id)
                .map(companyFound -> {
                    companyFound.setName(companyDTO.name());
                    companyFound.setPhoneNumber(companyDTO.phoneNumber());
                    companyFound.setUrlImage(companyDTO.urlImage());
                    return mapper.toDTO(repository.save(companyFound));
                }).orElseThrow(() -> new ResourceNotFoundException("Object not found. Id: " + id + ", Type: " + CompanyDTO.class.getSimpleName()));
    }

    public void delete(Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Object not found. Id: " + id + ", Type: " + CompanyDTO.class.getSimpleName())));
    }

    public void checkIfNameAlreadyExists(String name) {
        repository.findByName(name)
                .ifPresent(company -> {
                    throw new NameAlreadyExistsException("Name already exists. Name: " + name + ", Type: " + String.class.getSimpleName());
                });
    }

    public void checkIfPhoneNumberAlreadyExists(String phoneNumber) {
        repository.findByPhoneNumber(phoneNumber)
                .ifPresent(company -> {
                    throw new PhoneNumberAlreadyExistsException("Phone Number already exists. Phone Number: " + phoneNumber + ", Type: " + String.class.getSimpleName());
                });
    }

}
