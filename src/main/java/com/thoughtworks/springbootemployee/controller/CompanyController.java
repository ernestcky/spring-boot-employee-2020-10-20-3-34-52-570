package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.services.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;
    private CompanyMapper companyMapper;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }

    @GetMapping
    public List<CompanyResponse> findAll() {
        return this.companyService.findAll().stream().map(companyMapper::toResponse).collect(Collectors.toList());
    }

    @PostMapping
    public CompanyResponse create(@RequestBody CompanyRequest companyRequest) {
        return this.companyMapper.toResponse(this.companyService.create(this.companyMapper.toEntity(companyRequest)));
    }

    @GetMapping("/{companyId}")
    public CompanyResponse getCompany(@PathVariable String companyId) {
        return this.companyMapper.toResponse(this.companyService.getCompany(companyId));
    }

    @GetMapping("/{companyId}/employees")
    public List<Employee> getCompanyEmployees(@PathVariable String companyId) {
        return this.companyService.getCompanyEmployees(companyId);
    }

    @GetMapping(params = {
            "page",
            "pageSize"
    })
    public List<Company> getAll(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        return this.companyService.findAll(page, pageSize);
    }

    @PutMapping("/{companyId}")
    public Company update(@PathVariable String companyId, @RequestBody Company companyUpdate) {
        return this.companyService.update(companyId, companyUpdate);
    }

    @DeleteMapping("/{companyId}")
    public void delete(@PathVariable String companyId) {
        this.companyService.delete(companyId);
    }

}
