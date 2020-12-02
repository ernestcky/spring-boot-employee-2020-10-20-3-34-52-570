package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EmployeeServiceTest {
    @Test
    public void should_return_all_employee_when_get_all_given_repository_with_all_employee() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        List<Employee> expected = Arrays.asList(new Employee(), new Employee());
        when(employeeRepository.findAll()).thenReturn(expected);

        //when
        List<Employee> actual = employeeService.getAll();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_created_employee_when_create_given_an_empty_repository_and_employee_request() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        Employee expected = new Employee();
        when(employeeRepository.create(expected)).thenReturn(expected);

        //when
        Employee actual = employeeService.create(expected);

        //then
        assertEquals(expected, actual);
    }
    
    @Test
    public void should_return_correct_employee_when_get_employee_by_id_given_repository_and_employee_request() {
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        Employee expected = new Employee();
        expected.setId(1);

        when(employeeRepository.findEmployee(expected.getId())).thenReturn(expected);
                
        //when
        employeeService.create(expected);
        Employee actual = employeeService.findEmployee(1);

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void should_return_correct_employee_details_when_update_given_repository_and_employee() {
        //given
        EmployeeRepository employeeRepository = new EmployeeRepository();
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        Employee init = new Employee(1, "Ernest", 22, "M", 10000);

        Employee expected = new Employee(1, "Ernest", 23, "M", 10000);


        //when
        Employee actual = employeeService.update(init.getId(), expected);

        //then
        assertEquals(expected, actual);
    }

    

}