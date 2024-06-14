package com.lucasrznd.apigspot.services;

import com.lucasrznd.apigspot.models.TopMonthlyAnnouncer;
import com.lucasrznd.apigspot.repositories.TopMonthlyAnnouncerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopMonthlyAnnouncerService {

    private final TopMonthlyAnnouncerRepository topMonthlyAnnouncerRepository;

    public TopMonthlyAnnouncerService(TopMonthlyAnnouncerRepository topMonthlyAnnouncerRepository) {
        this.topMonthlyAnnouncerRepository = topMonthlyAnnouncerRepository;
    }

    public List<TopMonthlyAnnouncer> findTopMonthlyAnnouncers() {
        return topMonthlyAnnouncerRepository.findTopMonthlyAnnouncers();
    }

}
