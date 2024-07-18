package com.lucasrznd.apigspot.controllers.impl;

import com.lucasrznd.apigspot.dtos.request.SpotDTO;
import com.lucasrznd.apigspot.dtos.response.SpotResponse;
import com.lucasrznd.apigspot.services.SpotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/spot")
@RequiredArgsConstructor
public class SpotController {

    private final SpotService service;

    @GetMapping
    public ResponseEntity<List<SpotResponse>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok().body(service.count());
    }

    @GetMapping("/amount-raised")
    public ResponseEntity<Double> getAmountRaised() {
        return ResponseEntity.ok().body(service.getAmountRaised());
    }

    @GetMapping("/amount-raised-month")
    public ResponseEntity<BigDecimal> getAmountRaisedMonth() {
        return ResponseEntity.ok().body(service.getAmountRaisedMonth());
    }

    @GetMapping("/calculate-price")
    public ResponseEntity<Double> calculateSpotPrice(@RequestParam Double duration, @RequestParam boolean activeContract) {
        return ResponseEntity.ok().body(service.calculateSpotPrice(duration, activeContract));
    }

    @GetMapping("/latest")
    public ResponseEntity<List<SpotResponse>> findLatestSpots() {
        return ResponseEntity.ok().body(service.findLatestSpots());
    }

    @GetMapping("/search")
    public ResponseEntity<List<SpotDTO>> searchSpot(@RequestParam LocalDate initialDate, @RequestParam LocalDate finalDate,
                                                    @RequestParam(required = false) String companyName, @RequestParam(required = false) String announcerName) {
        return ResponseEntity.ok().body(service.getByDateRangeAnnouncerAndCompany(initialDate, finalDate, companyName, announcerName));
    }

    @PostMapping
    public ResponseEntity<SpotResponse> save(@RequestBody @Valid SpotDTO spotDTO) {
        return ResponseEntity.status(CREATED).body(service.save(spotDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpotResponse> update(@PathVariable Long id, @RequestBody @Valid SpotDTO spotDTO) {
        return ResponseEntity.ok().body(service.update(id, spotDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
