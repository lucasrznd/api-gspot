package com.lucasrznd.apigspot.controllers.impl;

import com.lucasrznd.apigspot.dtos.response.TopCompanyResponse;
import com.lucasrznd.apigspot.services.TopCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/top/companies")
@RequiredArgsConstructor
public class TopCompanyController {

    private final TopCompanyService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TopCompanyResponse>> findTopMonthlyCompanies() {
        return ResponseEntity.ok().body(service.findTopMonthlyCompanies());
    }

}
