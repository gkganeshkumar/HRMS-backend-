package com.example.Invoice_Hrms.controller;

import com.example.Invoice_Hrms.dto.CompanyDto;
import com.example.Invoice_Hrms.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/companies")

public class CompanyController {

    private final CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @PostMapping
    public CompanyDto createCompany(@RequestBody CompanyDto dto) {
        return service.createCompany(dto);
    }

    @GetMapping
    public List<CompanyDto> getAllCompanies() {
        return service.getAllCompanies();
    }

    @GetMapping("/{id}")
    public CompanyDto getCompanyById(@PathVariable String id) {
        return service.getCompanyById(id);
    }

    @PutMapping("/{id}")
    public CompanyDto updateCompany(@PathVariable String id, @RequestBody CompanyDto dto) {
        return service.updateCompany(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable String id) {
        service.deleteCompany(id);
    }

}


