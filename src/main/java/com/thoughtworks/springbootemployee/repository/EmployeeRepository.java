package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private List<Employee> employeeList = new ArrayList<>();

    public List<Employee> findAll() {
        return this.employeeList;
    }

    public List<Employee> findAll(String gender) {
        return null;
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
}
