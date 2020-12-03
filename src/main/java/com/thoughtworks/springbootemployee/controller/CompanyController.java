package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<Company> findAll() {
        return this.companyService.findAll();
    }

    @PostMapping
    public Company create(@RequestBody Company company) {
        return this.companyService.create(company);
    }

    @GetMapping("/{companyId}")
    public Company getCompany(@PathVariable String companyId) {
        return this.companyService.getCompany(companyId);
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
