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

    private final AnnouncerService announcerService;

    public AnnouncerController(AnnouncerService announcerService) {
        this.announcerService = announcerService;
    }

    @GetMapping
    public ResponseEntity<List<AnnouncerDTO>> findAll() {
        return ResponseEntity.ok().body(announcerService.findAll());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok().body(announcerService.count());
    }

    @PostMapping
    public ResponseEntity<AnnouncerDTO> save(@RequestBody @Valid AnnouncerDTO announcerDTO) {
        return ResponseEntity.status(CREATED).body(announcerService.save(announcerDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnnouncerDTO> update(@PathVariable Long id, @RequestBody @Valid AnnouncerDTO announcerDTO) {
        return ResponseEntity.ok().body(announcerService.update(id, announcerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        announcerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
