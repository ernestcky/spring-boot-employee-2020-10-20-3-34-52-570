package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeeController() {
        this.employeeService = new EmployeeService();
        this.employeeMapper = new EmployeeMapper();
    }

    @GetMapping
    public List<EmployeeResponse> getAll() {
        return this.employeeService.findAll().stream().map(employeeMapper::toResponse).collect(Collectors.toList());
    }

    @GetMapping(params = {
            "page",
            "pageSize"
    })
    public List<Employee> getAll(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        return this.employeeService.findAll(page, pageSize);
    }

    @GetMapping(params = {
            "gender"
    })
    public List<Employee> getAll(@RequestParam("gender") String gender) {
        return this.employeeService.findAll(gender);
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return this.employeeService.create(employee);
    }

    @PutMapping("/{employeeId}")
    public Employee update(@PathVariable String employeeId, @RequestBody Employee employeeUpdate) {
        return this.employeeService.update(employeeId, employeeUpdate);
    }

    @DeleteMapping("/{employeeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String employeeId) {
        this.employeeService.delete(employeeId);
    }

    @GetMapping("/{employeeId}")
    public Employee findEmployee(@PathVariable String employeeId) {
        return this.employeeService.findEmployee(employeeId);
    }
}
