package com.lucasrznd.apigspot.controllers.impl;

import com.lucasrznd.apigspot.controllers.TopCompanyController;
import com.lucasrznd.apigspot.dtos.response.TopCompanyResponse;
import com.lucasrznd.apigspot.services.TopCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TopCompanyControllerImpl implements TopCompanyController {

    private final TopCompanyService service;

    @Override
    public ResponseEntity<List<TopCompanyResponse>> findTopCompaniesOnCurrentMonth() {
        return ResponseEntity.ok().body(service.findTopCompaniesOnCurrentMonth());
    }

}
