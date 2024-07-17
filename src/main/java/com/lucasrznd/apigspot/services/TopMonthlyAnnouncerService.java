package com.lucasrznd.apigspot.services;

import com.lucasrznd.apigspot.dtos.request.TopMonthlyAnnouncerDTO;
import com.lucasrznd.apigspot.dtos.mappers.TopMonthlyAnnouncerMapper;
import com.lucasrznd.apigspot.repositories.TopMonthlyAnnouncerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopMonthlyAnnouncerService {

    private final TopMonthlyAnnouncerRepository topMonthlyAnnouncerRepository;
    private final TopMonthlyAnnouncerMapper mapper;

    public TopMonthlyAnnouncerService(TopMonthlyAnnouncerRepository topMonthlyAnnouncerRepository, TopMonthlyAnnouncerMapper mapper) {
        this.topMonthlyAnnouncerRepository = topMonthlyAnnouncerRepository;
        this.mapper = mapper;
    }

    public List<TopMonthlyAnnouncerDTO> findTopMonthlyAnnouncers() {
        return topMonthlyAnnouncerRepository.findTopMonthlyAnnouncers()
                .stream().map(mapper::toDTO).toList();
    }

}
