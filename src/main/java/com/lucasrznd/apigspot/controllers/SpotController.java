package com.lucasrznd.apigspot.controllers;

import com.lucasrznd.apigspot.dtos.request.SpotDTO;
import com.lucasrznd.apigspot.dtos.response.SpotResponse;
import com.lucasrznd.apigspot.services.SpotService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    public List<SpotResponse> findAll() {
        return spotService.findAll();
    }

    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public Long count() {
        return spotService.count();
    }

    @GetMapping("/amount-raised")
    @ResponseStatus(HttpStatus.OK)
    public Double getAmountRaised() {
        return spotService.getAmountRaised();
    }

    @GetMapping("/amount-raised-month")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getAmountRaisedMonth() {
        return spotService.getAmountRaisedMonth();
    }

    @GetMapping("/calculate-price")
    @ResponseStatus(HttpStatus.OK)
    public Double calculateSpotPrice(@RequestParam Double duration, @RequestParam boolean activeContract) {
        return spotService.calculateSpotPrice(duration, activeContract);
    }

    @GetMapping("/latest")
    @ResponseStatus(HttpStatus.OK)
    public List<SpotResponse> findLatestSpots() {
        return spotService.findLatestSpots();
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<SpotDTO> searchSpot(@RequestParam LocalDate initialDate, @RequestParam LocalDate finalDate,
                                    @RequestParam(required = false) String companyName, @RequestParam(required = false) String announcerName) {
        return spotService.getByDateRangeAnnouncerAndCompany(initialDate, finalDate, companyName, announcerName);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SpotResponse save(@RequestBody @Valid SpotDTO spotDTO) {
        return spotService.save(spotDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SpotResponse update(@PathVariable Long id, @RequestBody @Valid SpotDTO spotDTO) {
        return spotService.update(id, spotDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        spotService.delete(id);
    }

}
