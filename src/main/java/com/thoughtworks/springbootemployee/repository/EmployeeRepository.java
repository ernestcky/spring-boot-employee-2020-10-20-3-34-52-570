package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private List<Employee> employeeList = new ArrayList<>();

    public List<Employee> findAll() {
        return this.employeeList;
    }

    public Employee create(Employee employee) {
        this.employeeList.add(employee);
        return employee;
    }

    public Employee findEmployee(Integer employeeId) {
        return this.employeeList.stream().filter(employee -> employee.getId().equals(employeeId)).findFirst().orElse(null);
    }
}
