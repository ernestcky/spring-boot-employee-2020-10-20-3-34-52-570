package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public List<EmployeeResponse> getAll() {
        return this.employeeService.findAll().stream().map(employeeMapper::toResponse).collect(Collectors.toList());
    }

    @GetMapping(params = {
            "page",
            "pageSize"
    })
    public Page<EmployeeResponse> getAll(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        return this.employeeService.findAll(page, pageSize).map(employeeMapper::toResponse);
    }

    @GetMapping(params = {
            "gender"
    })
    public List<EmployeeResponse> getAll(@RequestParam("gender") String gender) {
        return this.employeeService.findAll(gender).stream().map(employeeMapper::toResponse).collect(Collectors.toList());
    }

    @PostMapping
    public EmployeeResponse create(@RequestBody EmployeeRequest employeeRequest) {
        return this.employeeMapper.toResponse(this.employeeService.create(employeeMapper.toEntity(employeeRequest)));
    }

    @PutMapping("/{employeeId}")
    public EmployeeResponse update(@PathVariable String employeeId, @RequestBody EmployeeRequest employeeRequest) {
        return this.employeeMapper.toResponse(this.employeeService.update(employeeId, this.employeeMapper.toEntity(employeeRequest)));
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
