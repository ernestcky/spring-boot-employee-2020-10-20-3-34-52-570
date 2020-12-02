package com.thoughtworks.springbootemployee.entity;

import java.util.List;

public class Company {
    private Integer id;
    private String companyName;
    private Integer employeesNumber;
    private List<Employee> employees;

    public Integer getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Integer getEmployeesNumber() {
        return employeesNumber;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
