package com.lucasrznd.apigspot.services;

import com.lucasrznd.apigspot.dtos.AnnouncerDTO;
import com.lucasrznd.apigspot.dtos.mappers.AnnouncerMapper;
import com.lucasrznd.apigspot.repositories.AnnouncerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncerService {

    private AnnouncerRepository announcerRepository;
    private AnnouncerMapper announcerMapper;

    public AnnouncerService(AnnouncerRepository repository, AnnouncerMapper announcerMapper) {
        this.announcerRepository = repository;
        this.announcerMapper = announcerMapper;
    }

    public List<AnnouncerDTO> selectAll() {
        return announcerRepository.findAll().stream().map(announcerMapper::toDTO).toList();
    }

    public AnnouncerDTO insert(AnnouncerDTO announcerDTO) {
        return announcerMapper.toDTO(announcerRepository.save(announcerMapper.toModel(announcerDTO)));
    }

    public AnnouncerDTO update(Long id, AnnouncerDTO announcerDTO) {
        return announcerRepository.findById(id)
                .map(announcerFound -> {
                    announcerFound.setName(announcerDTO.name());
                    announcerFound.setPhoneNumber(announcerDTO.phoneNumber());
                    announcerFound.setUrlImage(announcerDTO.urlImage());
                    return announcerMapper.toDTO(announcerRepository.save(announcerFound));
                }).get();
    }

    public void delete(Long id) {
        announcerRepository.deleteById(id);
    }

}
