package com.lucasrznd.apigspot.controllers;

import com.lucasrznd.apigspot.dtos.CompanyDTO;
import com.lucasrznd.apigspot.services.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CompanyDTO> findAll() {
        return companyService.findAll();
    }

    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public Long count() {
        return companyService.count();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyDTO save(@RequestBody @Valid CompanyDTO companyDTO) {
        return companyService.save(companyDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyDTO update(@PathVariable Long id, @RequestBody @Valid CompanyDTO companyDTO) {
        return companyService.update(id, companyDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        companyService.delete(id);
    }

}
