package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyRepository {
    private List<Company> companyList = new ArrayList<>();

    public List<Company> findAll() {
        return this.companyList;
    }

    public Company create(Company company) {
        this.companyList.add(company);
        return company;
    }
}
