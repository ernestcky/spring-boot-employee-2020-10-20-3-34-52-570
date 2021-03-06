package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exceptions.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return this.employeeRepository.findAll();
    }

    public List<Employee> findAll(String gender) {
        return this.employeeRepository.findAllByGender(gender);
    }

    public Page<Employee> findAll(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Employee> employeePage = this.employeeRepository.findAll(pageable);
        return employeePage;
    }

    public Employee create(Employee employee) {
        return this.employeeRepository.insert(employee);
    }

    public Employee findEmployee(String employeeId) {
        return this.employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found."));
    }

    public Employee update(String employeeId, Employee employeeUpdate) {
        if (!this.employeeRepository.existsById(employeeId)) {
            throw new RuntimeException();
        }
        employeeUpdate.setId(employeeId);
        return this.employeeRepository.save(employeeUpdate);
    }
    public void delete(String employeeId) {
        this.employeeRepository.deleteById(employeeId);
    }
}
