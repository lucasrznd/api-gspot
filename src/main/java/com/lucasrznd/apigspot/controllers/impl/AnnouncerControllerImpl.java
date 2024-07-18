package com.lucasrznd.apigspot.controllers.impl;

import com.lucasrznd.apigspot.controllers.AnnouncerController;
import com.lucasrznd.apigspot.dtos.request.AnnouncerDTO;
import com.lucasrznd.apigspot.services.AnnouncerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class AnnouncerControllerImpl implements AnnouncerController {

    private final AnnouncerService service;

    @Override
    public ResponseEntity<List<AnnouncerDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @Override
    public ResponseEntity<Long> countAnnouncers() {
        return ResponseEntity.ok().body(service.count());
    }

    @Override
    public ResponseEntity<AnnouncerDTO> saveAnnouncer(AnnouncerDTO announcerDTO) {
        return ResponseEntity.status(CREATED).body(service.save(announcerDTO));
    }

    @Override
    public ResponseEntity<AnnouncerDTO> update(Long id, AnnouncerDTO announcerDTO) {
        return ResponseEntity.ok().body(service.update(id, announcerDTO));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
