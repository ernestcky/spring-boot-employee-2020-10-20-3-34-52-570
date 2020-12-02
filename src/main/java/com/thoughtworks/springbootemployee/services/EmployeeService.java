package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAll() {
        return this.employeeRepository.findAll();
    }

    public List<Employee> getAll(String gender) {
        return this.employeeRepository.findAll(gender);
    }

    public List<Employee> getAll(Integer page, Integer pageSize) {
        return this.employeeRepository.findAll(page, pageSize);
    }

    public Employee create(Employee employee) {
        return this.employeeRepository.create(employee);
    }

    public Employee findEmployee(Integer employeeId) {
        return this.employeeRepository.findEmployee(employeeId);
    }

    public Employee update(Integer employeeId, Employee employeeUpdate) {
        return this.employeeRepository.update(employeeId, employeeUpdate);
    }
}
