package com.lucasrznd.apigspot.controllers;

import com.lucasrznd.apigspot.dtos.SpotDTO;
import com.lucasrznd.apigspot.services.SpotService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/spot")
public class SpotController {

    private final SpotService spotService;

    public SpotController(SpotService spotService) {
        this.spotService = spotService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SpotDTO> selectAll() {
        return spotService.selectAll();
    }

    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public Long countSpots() {
        return spotService.countSpots();
    }

    @GetMapping("/amount-raised")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getAmountRaised() {
        return spotService.getAmountRaised();
    }

    @GetMapping("/calculate-price")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal calculateSpotPrice(@RequestParam Double duration, @RequestParam boolean activeContract) {
        return spotService.calculateSpotPrice(duration, activeContract);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SpotDTO insert(@RequestBody SpotDTO spotDTO) {
        return spotService.insert(spotDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SpotDTO update(@PathVariable Long id, @RequestBody SpotDTO spotDTO) {
        return spotService.update(id, spotDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        spotService.delete(id);
    }

}
