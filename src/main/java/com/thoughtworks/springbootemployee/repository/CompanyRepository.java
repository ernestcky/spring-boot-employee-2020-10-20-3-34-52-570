package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.entity.Company;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyRepository {
    private List<Company> companyList;

    public List<Company> findAll() {
        return null;
    }
}
