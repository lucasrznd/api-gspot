package com.lucasrznd.apigspot.controllers;

import com.lucasrznd.apigspot.dtos.request.AnnouncerDTO;
import com.lucasrznd.apigspot.services.AnnouncerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/announcer")
public class AnnouncerController {

    private final AnnouncerService announcerService;

    public AnnouncerController(AnnouncerService announcerService) {
        this.announcerService = announcerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AnnouncerDTO> findAll() {
        return announcerService.findAll();
    }

    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public Long count() {
        return announcerService.count();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnnouncerDTO save(@RequestBody @Valid AnnouncerDTO announcerDTO) {
        return announcerService.save(announcerDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AnnouncerDTO update(@PathVariable Long id, @RequestBody @Valid AnnouncerDTO announcerDTO) {
        return announcerService.update(id, announcerDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        announcerService.delete(id);
    }

}
