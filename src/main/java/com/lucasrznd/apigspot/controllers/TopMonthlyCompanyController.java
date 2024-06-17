package com.lucasrznd.apigspot.controllers;

import com.lucasrznd.apigspot.dtos.TopMonthlyCompanyDTO;
import com.lucasrznd.apigspot.services.TopMonthlyCompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/monthly/company")
public class TopMonthlyCompanyController {

    private final TopMonthlyCompanyService topMonthlyCompanyService;

    public TopMonthlyCompanyController(TopMonthlyCompanyService topMonthlyCompanyService) {
        this.topMonthlyCompanyService = topMonthlyCompanyService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TopMonthlyCompanyDTO> findTopMonthlyCompanies() {
        return topMonthlyCompanyService.findTopMonthlyCompanies();
    }

}
