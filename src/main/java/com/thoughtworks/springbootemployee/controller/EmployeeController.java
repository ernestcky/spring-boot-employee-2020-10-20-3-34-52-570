package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
//    private List<Employee> employeeList = new ArrayList<>();
    private EmployeeService employeeService = new EmployeeService(new EmployeeRepository());

    @GetMapping
    public List<Employee> getAll() {
        return this.employeeService.getAll();
    }

//    @GetMapping(params = {
//        "page",
//        "pageSize"
//    })
//    public List<Employee> getAll(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
//        int pageToSkip = page - 1;
//        int numberOfEmployeesToSkip = pageToSkip * pageSize;
//
//        return employeeList.stream()
//            .skip(numberOfEmployeesToSkip)
//            .limit(pageSize)
//            .collect(Collectors.toList());
//    }
//
//    @GetMapping(params = {
//        "gender"
//    })
//    public List<Employee> getAll(@RequestParam("gender") String gender) {
//        return employeeList.stream()
//            .filter(employee -> gender.equals(employee.getGender()))
//            .collect(Collectors.toList());
//    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return this.employeeService.create(employee);
    }

    @PutMapping("/{employeeId}")
    public Employee update(@PathVariable Integer employeeId, @RequestBody Employee employeeUpdate) {
        return this.employeeService.update(employeeId, employeeUpdate);
    }
//
//    @DeleteMapping("/{employeeId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable Integer employeeId) {
//        this.employeeList.stream()
//            .filter(employee -> employeeId.equals(employee.getId()))
//            .findFirst()
//            .ifPresent(employee -> this.employeeList.remove(employee));
//    }
//
    @GetMapping("/{employeeId}")
    public Employee findEmployee(@PathVariable Integer employeeId) {
        return this.employeeService.findEmployee(employeeId);
    }
}
