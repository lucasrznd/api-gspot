package com.lucasrznd.apigspot.services;

import com.lucasrznd.apigspot.dtos.AnnouncerDTO;
import com.lucasrznd.apigspot.dtos.mappers.AnnouncerMapper;
import com.lucasrznd.apigspot.exceptions.common.NameAlreadyExistsException;
import com.lucasrznd.apigspot.exceptions.common.PhoneNumberAlreadyExistsException;
import com.lucasrznd.apigspot.exceptions.common.ResourceNotFoundException;
import com.lucasrznd.apigspot.repositories.AnnouncerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncerService {

    private final AnnouncerRepository repository;
    private final AnnouncerMapper mapper;

    public AnnouncerService(AnnouncerRepository repository, AnnouncerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<AnnouncerDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDTO).toList();
    }

    public Long count() {
        return repository.count();
    }

    public AnnouncerDTO save(AnnouncerDTO announcerDTO) {
        checkIfNameAlreadyExists(announcerDTO.name());
        checkIfPhoneNumberAlreadyExists(announcerDTO.phoneNumber());

        return mapper.toDTO(repository.save(mapper.toModel(announcerDTO)));
    }

    public AnnouncerDTO update(Long id, AnnouncerDTO announcerDTO) {
        return repository.findById(id)
                .map(announcerFound -> {
                    announcerFound.setName(announcerDTO.name());
                    announcerFound.setPhoneNumber(announcerDTO.phoneNumber());
                    announcerFound.setUrlImage(announcerDTO.urlImage());
                    return mapper.toDTO(repository.save(announcerFound));
                }).orElseThrow(() -> new ResourceNotFoundException("Object not found. Id: " + id + ", Type: " + AnnouncerDTO.class.getSimpleName()));
    }

    public void delete(Long id) {
        repository.delete(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Object not found. Id: " + id + ", Type: " + AnnouncerDTO.class.getSimpleName())));
    }

    public void checkIfNameAlreadyExists(String name) {
        repository.findByName(name)
                .ifPresent(announcer -> {
                    throw new NameAlreadyExistsException("Name already exists. Name: " + name + ", Type: " + String.class.getSimpleName());
                });
    }

    public void checkIfPhoneNumberAlreadyExists(String phoneNumber) {
        repository.findByPhoneNumber(phoneNumber)
                .ifPresent(announcer -> {
                    throw new PhoneNumberAlreadyExistsException("Phone Number already exists. Phone Number: " + phoneNumber + ", Type: " + String.class.getSimpleName());
                });
    }

}
