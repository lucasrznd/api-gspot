package com.lucasrznd.apigspot.controllers;

import com.lucasrznd.apigspot.dtos.CompanyDTO;
import com.lucasrznd.apigspot.services.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CompanyDTO> selectAll() {
        return companyService.selectAll();
    }

    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public Long countCompanies() {
        return companyService.countCompanies();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyDTO insert(@RequestBody CompanyDTO companyDTO) {
        return companyService.insert(companyDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyDTO update(@PathVariable Long id, @RequestBody CompanyDTO companyDTO) {
        return companyService.update(id, companyDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        companyService.delete(id);
    }

}
