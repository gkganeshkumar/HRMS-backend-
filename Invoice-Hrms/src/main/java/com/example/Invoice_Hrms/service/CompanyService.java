package com.example.Invoice_Hrms.service;

import com.example.Invoice_Hrms.dto.CompanyDto;
import com.example.Invoice_Hrms.mapper.CompanyMapper;
import com.example.Invoice_Hrms.model.Company;
import com.example.Invoice_Hrms.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class CompanyService {
    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public CompanyDto createCompany(CompanyDto dto) {
        Company company = CompanyMapper.toEntity(dto);
        Company saved = repository.save(company);
        return CompanyMapper.toDto(saved);
    }

    public List<CompanyDto> getAllCompanies() {
        return repository.findAll().stream()
                .map(CompanyMapper::toDto)
                .collect(Collectors.toList());
    }

    public CompanyDto getCompanyById(String id) {
        Optional<Company> optional = repository.findById(id);
        return optional.map(CompanyMapper::toDto).orElse(null);
    }

    public CompanyDto updateCompany(String id, CompanyDto dto) {
        Optional<Company> optional = repository.findById(id);
        if (optional.isPresent()) {
            Company existing = optional.get();
            Company updated = CompanyMapper.toEntity(dto);
            updated.setId(existing.getId());
            Company saved = repository.save(updated);
            return CompanyMapper.toDto(saved);
        }
        return null;
    }

    public void deleteCompany(String id) {
        repository.deleteById(id);
    }

}
