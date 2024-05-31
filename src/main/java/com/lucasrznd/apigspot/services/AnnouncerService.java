package com.lucasrznd.apigspot.services;

import com.lucasrznd.apigspot.dtos.AnnouncerDTO;
import com.lucasrznd.apigspot.dtos.mappers.AnnouncerMapper;
import com.lucasrznd.apigspot.exceptions.announcer.AnnouncerNotFoundException;
import com.lucasrznd.apigspot.exceptions.announcer.DuplicateAnnouncerException;
import com.lucasrznd.apigspot.repositories.AnnouncerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        AnnouncerDTO announcerFound = findByNameAndPhoneNumber(announcerDTO.name(), announcerDTO.phoneNumber().trim().toLowerCase());

        if (announcerFound != null) {
            throw new DuplicateAnnouncerException("Announcer already exists.");
        }

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

    private AnnouncerDTO findByNameAndPhoneNumber(String name, String phoneNumber) {
        return announcerMapper.toDTO(announcerRepository.findByNameAndPhoneNumber(name, phoneNumber));
    }

}
