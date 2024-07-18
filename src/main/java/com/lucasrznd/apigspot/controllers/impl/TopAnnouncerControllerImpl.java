package com.lucasrznd.apigspot.controllers.impl;

import com.lucasrznd.apigspot.controllers.TopAnnouncerController;
import com.lucasrznd.apigspot.dtos.response.TopAnnouncerResponse;
import com.lucasrznd.apigspot.services.TopAnnouncerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TopAnnouncerControllerImpl implements TopAnnouncerController {

    private final TopAnnouncerService service;

    @Override
    public ResponseEntity<List<TopAnnouncerResponse>> findTopAnnouncersOnCurrentMonth() {
        return ResponseEntity.ok().body(service.findTopAnnouncersOnCurrentMonth());
    }

}
