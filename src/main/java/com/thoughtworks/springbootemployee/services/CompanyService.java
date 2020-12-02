package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAll() {
        return this.companyRepository.findAll();
    }

    public Company create(Company company) {
        return this.companyRepository.create(company);
    }

    public Company getCompany(Integer companyId) {
        return this.companyRepository.getCompany(companyId);
    }

    public List<Employee> getCompanyEmployees(Integer companyId) {
        return this.companyRepository.getCompanyEmployees(companyId);
    }

    public List<Company> findAll(int page, int pageSize) {
        return this.companyRepository.findAll(page, pageSize);
    }

    public Company update(Integer companyId, Company companyUpdate) {
        return this.companyRepository.update(companyId, companyUpdate);
    }
}
