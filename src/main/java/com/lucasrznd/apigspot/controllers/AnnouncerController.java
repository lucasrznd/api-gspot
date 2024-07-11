package com.lucasrznd.apigspot.controllers;

import com.lucasrznd.apigspot.dtos.AnnouncerDTO;
import com.lucasrznd.apigspot.services.AnnouncerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/announcer")
public class AnnouncerController {

    private AnnouncerService announcerService;

    public AnnouncerController(AnnouncerService announcerService) {
        this.announcerService = announcerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AnnouncerDTO> selectAll() {
        return announcerService.selectAll();
    }

    @GetMapping("/countAnnouncers")
    @ResponseStatus(HttpStatus.OK)
    public Long countAnnouncers() {
        return announcerService.countAnnouncers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnnouncerDTO insert(@Valid @RequestBody AnnouncerDTO announcerDTO) {
        return announcerService.insert(announcerDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AnnouncerDTO update(@PathVariable Long id, @RequestBody AnnouncerDTO announcerDTO) {
        return announcerService.update(id, announcerDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        announcerService.delete(id);
    }

}
