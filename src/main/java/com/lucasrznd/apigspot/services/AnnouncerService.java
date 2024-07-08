package com.lucasrznd.apigspot.services;

import com.lucasrznd.apigspot.dtos.AnnouncerDTO;
import com.lucasrznd.apigspot.dtos.mappers.AnnouncerMapper;
import com.lucasrznd.apigspot.exceptions.announcer.AnnouncerNotFoundException;
import com.lucasrznd.apigspot.exceptions.common.NameAlreadyExistsException;
import com.lucasrznd.apigspot.exceptions.common.PhoneNumberAlreadyExistsException;
import com.lucasrznd.apigspot.repositories.AnnouncerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnnouncerService {

    private final AnnouncerRepository announcerRepository;
    private final AnnouncerMapper announcerMapper;

    public AnnouncerService(AnnouncerRepository repository, AnnouncerMapper announcerMapper) {
        this.announcerRepository = repository;
        this.announcerMapper = announcerMapper;
    }

    public List<AnnouncerDTO> selectAll() {
        return announcerRepository.findAll().stream().map(announcerMapper::toDTO).toList();
    }

    public Long countAnnouncers() {
        return announcerRepository.count();
    }

    public AnnouncerDTO insert(AnnouncerDTO announcerDTO) {
        checkIfNameAlreadyExists(announcerDTO.name());
        checkIfPhoneNumberAlreadyExists(announcerDTO.phoneNumber());

        return announcerMapper.toDTO(announcerRepository.save(announcerMapper.toModel(announcerDTO)));
    }

    public AnnouncerDTO update(Long id, AnnouncerDTO announcerDTO) {
        return announcerRepository.findById(id)
                .map(announcerFound -> {
                    announcerFound.setName(announcerDTO.name());
                    announcerFound.setPhoneNumber(announcerDTO.phoneNumber());
                    announcerFound.setUrlImage(announcerDTO.urlImage());
                    return announcerMapper.toDTO(announcerRepository.save(announcerFound));
                }).orElseThrow(() -> new AnnouncerNotFoundException("Announcer not found."));
    }

    public void delete(Long id) {
        announcerRepository.delete(announcerRepository.findById(id).orElseThrow(() -> new AnnouncerNotFoundException("Announcer not found.")));
    }

    public void checkIfNameAlreadyExists(String name) {
        Optional<AnnouncerDTO> announcerFounded = findByName(name);

        if (announcerFounded.isPresent()) {
            throw new NameAlreadyExistsException("Name already exists.");
        }
    }

    public void checkIfPhoneNumberAlreadyExists(String phoneNumber) {
        Optional<AnnouncerDTO> announcerFounded = findByPhoneNumber(phoneNumber);

        if (announcerFounded.isPresent()) {
            throw new PhoneNumberAlreadyExistsException("Phone Number already exists.");
        }
    }

    public Optional<AnnouncerDTO> findByName(String name) {
        return announcerRepository.findByName(name).isPresent()
                ? Optional.of(announcerMapper.toDTO(announcerRepository.findByName(name).get()))
                : Optional.empty();
    }

    public Optional<AnnouncerDTO> findByPhoneNumber(String phoneNumber) {
        return announcerRepository.findByPhoneNumber(phoneNumber).isPresent()
                ? Optional.of(announcerMapper.toDTO(announcerRepository.findByPhoneNumber(phoneNumber).get()))
                : Optional.empty();
    }

}
