package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private List<Employee> employeeList = new ArrayList<>();

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public List<Employee> findAll() {
        return this.employeeList;
    }

    public List<Employee> findAll(String gender) {
        return this.employeeList.stream()
            .filter(employee -> gender.equals(employee.getGender()))
            .collect(Collectors.toList());
    }

    public List<Employee> findAll(Integer page, Integer pageSize) {
        int pageToSkip = page - 1;

        return this.employeeList.stream()
            .skip(pageToSkip * pageSize)
            .limit(pageSize)
            .collect(Collectors.toList());
    }

    public Employee create(Employee employee) {
        this.employeeList.add(employee);
        return employee;
    }

    public Employee findEmployee(Integer employeeId) {
        return this.employeeList.stream().filter(employee -> employee.getId().equals(employeeId)).findFirst().orElse(null);
    }

    public Employee update(Integer employeeId, Employee employeeUpdate) {
        this.employeeList.stream()
            .filter(employee -> employeeId.equals(employee.getId()))
            .findFirst()
            .ifPresent(employee -> {
                this.employeeList.remove(employee);
                this.employeeList.add(employeeUpdate);
            });

        return employeeUpdate;
    }

    public void delete(Integer employeeId) {
        this.employeeList.stream()
                .filter(employee -> employeeId.equals(employee.getId()))
                .findFirst()
                .ifPresent(employee -> this.employeeList.remove(employee));
    }
}
