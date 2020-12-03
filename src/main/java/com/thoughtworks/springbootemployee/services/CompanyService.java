package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> findAll() {
        return this.companyRepository.findAll();
    }

    public Company create(Company company) {
        return this.companyRepository.insert(company);
    }

    public Company getCompany(String companyId) {
        return this.companyRepository.findById(companyId).orElseThrow(RuntimeException::new);
    }

    public List<Employee> getCompanyEmployees(String companyId) {
        return this.companyRepository.findById(companyId).orElseThrow(RuntimeException::new).getEmployees();
    }

    public List<Company> findAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.companyRepository.findAll(pageable).toList();
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
