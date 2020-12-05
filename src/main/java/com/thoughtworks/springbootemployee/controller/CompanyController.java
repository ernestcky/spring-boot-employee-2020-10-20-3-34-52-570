package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.services.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;
    private CompanyMapper companyMapper;
    private EmployeeMapper employeeMapper;

    public CompanyController(CompanyService companyService, CompanyMapper companyMapper, EmployeeMapper employeeMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
        this.employeeMapper = employeeMapper;
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
    public List<EmployeeResponse> getCompanyEmployees(@PathVariable String companyId) {
        return this.companyService.getCompanyEmployees(companyId).stream().map(employeeMapper::toResponse).collect(Collectors.toList());
    }

    @GetMapping(params = {
            "page",
            "pageSize"
    })
    public Page<CompanyResponse> getAll(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        return this.companyService.findAll(page, pageSize).map(companyMapper::toResponse);
    }

    @PutMapping("/{companyId}")
    public CompanyResponse update(@PathVariable String companyId, @RequestBody CompanyRequest companyUpdate) {
        return this.companyMapper.toResponse(this.companyService.update(companyId, this.companyMapper.toEntity(companyUpdate)));
    }

    @DeleteMapping("/{companyId}")
    public void delete(@PathVariable String companyId) {
        this.companyService.delete(companyId);
    }

}
