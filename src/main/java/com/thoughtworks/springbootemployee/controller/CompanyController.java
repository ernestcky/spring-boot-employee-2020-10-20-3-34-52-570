package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.services.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService = new CompanyService(new CompanyRepository());

    @GetMapping
    public List<Company> findAll() {
        return this.companyService.findAll();
    }

    @PostMapping
    public Company create(@RequestBody Company company) {
        return this.companyService.create(company);
    }

    @GetMapping("/{companyId}")
    public Company getCompany(@PathVariable Integer companyId) {
        return this.companyService.getCompany(companyId);
    }

    @GetMapping("/{companyId}/employees")
    public Company getCompanyEmployees(@PathVariable Integer companyId) {
        return this.companyService.getCompanyEmployees(companyId);
    }

}
