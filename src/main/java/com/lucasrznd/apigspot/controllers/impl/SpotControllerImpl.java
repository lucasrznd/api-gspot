package com.lucasrznd.apigspot.controllers.impl;

import com.lucasrznd.apigspot.controllers.SpotController;
import com.lucasrznd.apigspot.dtos.request.SpotDTO;
import com.lucasrznd.apigspot.dtos.response.SpotResponse;
import com.lucasrznd.apigspot.services.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class SpotControllerImpl implements SpotController {

    private final SpotService service;

    @Override
    public ResponseEntity<List<SpotResponse>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @Override
    public ResponseEntity<Long> countSpots() {
        return ResponseEntity.ok().body(service.count());
    }

    @Override
    public ResponseEntity<Double> getTotalAmountRaised() {
        return ResponseEntity.ok().body(service.getTotalAmountRaised());
    }

    @Override
    public ResponseEntity<Double> getAmountRaisedCurrentMonth() {
        return ResponseEntity.ok().body(service.getAmountRaisedCurrentMonth());
    }

    @Override
    public ResponseEntity<Double> calculateSpotPrice(Double duration, boolean activeContract) {
        return ResponseEntity.ok().body(service.calculateSpotPrice(duration, activeContract));
    }

    @Override
    public ResponseEntity<List<SpotResponse>> findLatestSpots() {
        return ResponseEntity.ok().body(service.findLatestSpots());
    }

    @Override
    public ResponseEntity<List<SpotResponse>> searchSpot(LocalDate initialDate, LocalDate finalDate,
                                                    String companyName, String announcerName) {
        return ResponseEntity.ok().body(service.findByDateRangeAnnouncerAndCompany(initialDate, finalDate, companyName, announcerName));
    }

    @Override
    public ResponseEntity<SpotResponse> saveSpot(SpotDTO spotDTO) {
        return ResponseEntity.status(CREATED).body(service.save(spotDTO));
    }

    @Override
    public ResponseEntity<SpotResponse> update(Long id, SpotDTO spotDTO) {
        return ResponseEntity.ok().body(service.update(id, spotDTO));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
