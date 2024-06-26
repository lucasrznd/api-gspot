package com.lucasrznd.apigspot.controllers;

import com.lucasrznd.apigspot.dtos.TopMonthlyAnnouncerDTO;
import com.lucasrznd.apigspot.services.TopMonthlyAnnouncerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("monthly/announcer")
public class TopMonthlyAnnouncerController {

    private final TopMonthlyAnnouncerService topMonthlyAnnouncerService;

    public TopMonthlyAnnouncerController(TopMonthlyAnnouncerService topMonthlyAnnouncerService) {
        this.topMonthlyAnnouncerService = topMonthlyAnnouncerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TopMonthlyAnnouncerDTO> findTopMonthlyAnnouncers() {
        return topMonthlyAnnouncerService.findTopMonthlyAnnouncers();
    }

}
