package com.lucasrznd.apigspot.services;

import com.lucasrznd.apigspot.dtos.mappers.TopAnnouncerMapper;
import com.lucasrznd.apigspot.dtos.response.TopAnnouncerResponse;
import com.lucasrznd.apigspot.repositories.TopAnnouncerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopAnnouncerService {

    private final TopAnnouncerRepository service;
    private final TopAnnouncerMapper mapper;

    public List<TopAnnouncerResponse> findTopAnnouncersOnCurrentMonth() {
        return service.findTopAnnouncersOnCurrentMonth()
                .stream().map(mapper::toDTO).toList();
    }

}
