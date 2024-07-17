package com.lucasrznd.apigspot.controllers;

import com.lucasrznd.apigspot.dtos.response.TopAnnouncerResponse;
import com.lucasrznd.apigspot.services.TopAnnouncerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("top/announcers")
@RequiredArgsConstructor
public class TopAnnouncerController {

    private final TopAnnouncerService service;

    @GetMapping
    public ResponseEntity<List<TopAnnouncerResponse>> findTopMonthlyAnnouncers() {
        return ResponseEntity.ok().body(service.findTopMonthlyAnnouncers());
    }

}
