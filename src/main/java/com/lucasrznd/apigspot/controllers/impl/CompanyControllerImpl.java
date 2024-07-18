package com.lucasrznd.apigspot.controllers.impl;

import com.lucasrznd.apigspot.controllers.CompanyController;
import com.lucasrznd.apigspot.dtos.request.CompanyDTO;
import com.lucasrznd.apigspot.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class CompanyControllerImpl implements CompanyController {

    private final CompanyService service;

    @Override
    public ResponseEntity<List<CompanyDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @Override
    public ResponseEntity<Long> countCompanies() {
        return ResponseEntity.ok().body(service.count());
    }

    @Override
    public ResponseEntity<CompanyDTO> saveCompany(CompanyDTO companyDTO) {
        return ResponseEntity.status(CREATED).body(service.save(companyDTO));
    }

    @Override
    public ResponseEntity<CompanyDTO> update(Long id, CompanyDTO companyDTO) {
        return ResponseEntity.ok().body(service.update(id, companyDTO));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
