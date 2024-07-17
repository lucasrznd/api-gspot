package com.lucasrznd.apigspot.controllers;

import com.lucasrznd.apigspot.dtos.request.AnnouncerDTO;
import com.lucasrznd.apigspot.services.AnnouncerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController()
@RequestMapping("/announcer")
public class AnnouncerController {

    private final AnnouncerService service;

    public AnnouncerController(AnnouncerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AnnouncerDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok().body(service.count());
    }

    @PostMapping
    public ResponseEntity<AnnouncerDTO> save(@RequestBody @Valid AnnouncerDTO announcerDTO) {
        return ResponseEntity.status(CREATED).body(service.save(announcerDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnnouncerDTO> update(@PathVariable Long id, @RequestBody @Valid AnnouncerDTO announcerDTO) {
        return ResponseEntity.ok().body(service.update(id, announcerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
