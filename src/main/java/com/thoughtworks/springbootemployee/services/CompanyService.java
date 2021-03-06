package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exceptions.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Company> findAll() {
        return this.companyRepository.findAll();
    }

    public Company create(Company company) {
        return this.companyRepository.insert(company);
    }

    public Company getCompany(String companyId) {
        return this.companyRepository.findById(companyId).orElseThrow(() -> new CompanyNotFoundException("Company Not Found."));
    }

    public List<Employee> getCompanyEmployees(String companyId) {
        // todo: check exist first
        if (this.companyRepository.existsById(companyId)) {
            return this.employeeRepository.findAllByCompanyId(companyId);
        }
        else throw new CompanyNotFoundException("Company Not Found.");
    }

    public Page<Company> findAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return this.companyRepository.findAll(pageable);
    }

    public Company update(String companyId, Company companyUpdate) {
        if (!this.companyRepository.existsById(companyId)) {
            throw new RuntimeException();
        }
        companyUpdate.setCompanyId(companyId);

        return this.companyRepository.save(companyUpdate);
    }

    public void delete(String companyId) {
        this.companyRepository.deleteById(companyId);
    }
}
