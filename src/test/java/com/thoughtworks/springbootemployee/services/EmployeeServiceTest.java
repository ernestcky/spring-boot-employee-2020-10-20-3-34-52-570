package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeRepository employeeRepository;

    @Test
    public void should_return_all_employee_when_get_all_given_repository_with_all_employee() {
        //given
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
        Employee expected = new Employee();
        when(employeeRepository.insert(expected)).thenReturn(expected);

        //when
        Employee actual = employeeService.create(expected);

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void should_return_correct_employee_when_get_employee_by_id_given_repository_and_employee_request() {
        //given
        Employee expected = new Employee();
        expected.setId("1");

        when(employeeRepository.findById(expected.getId())).thenReturn(Optional.of(expected));

        //when
        Employee actual = employeeService.findEmployee("1");

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void should_return_correct_employee_details_when_update_given_repository_and_employee() {
        //given
        Employee init = new Employee("1", "Ernest", 22, "M", 10000);
        Employee expected = new Employee("1", "Ernest", 23, "M", 10000);

        when(employeeRepository.existsById(init.getId())).thenReturn(true);
        when(employeeRepository.save(expected)).thenReturn(expected);

        //when
        Employee actual = employeeService.update(init.getId(), expected);

        //then
        assertEquals(expected, actual);
    }

//    @Test
//    public void should_return_female_employ_when_get_by_gender_given_param_is_female() {
//        //given
//        List<Employee> init = Arrays.asList(
//                new Employee(null, null, null, "F", null),
//                new Employee(null, null, null, "M", null),
//                new Employee(null, null, null, "F", null)
//        );
//        List<Employee> expected = init.stream().filter(employee -> employee.getGender().equals("F")).collect(Collectors.toList());
//
//        //when //Todo: remove logic
//        init.stream().forEach(employee -> employeeService.create(employee));
//        List<Employee> actual = employeeService.getAll("F");
//
//        //then
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void should_return_two_employee_when_get_all_with_page_size_given_repository() {
//        //given
//        List<Employee> init = Arrays.asList(
//                new Employee(1, null, null, "F", null),
//                new Employee(2, null, null, "M", null),
//                new Employee(3, null, null, "F", null),
//                new Employee(4, null, null, "F", null),
//                new Employee(5, null, null, "F", null)
//        );
//        //todo: remove logic
//        List<Employee> expected = init.stream().filter(employee -> employee.getId().equals(3) || employee.getId().equals(4)).collect(Collectors.toList());
//        // todo: use mock
//        //when
//        init.stream().forEach(employee -> employeeService.create(employee));
//        List<Employee> actual = employeeService.getAll(2, 2);
//
//        //then
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void should_delete_successfully_when_delete_given_repository_and_employee_list() {
//        //given
//        // todo: mock
//        EmployeeRepository employeeRepository = new EmployeeRepository();
//        EmployeeService employeeService = new EmployeeService(employeeRepository);
//        Employee init = new Employee();
//        init.setId(1);
//        employeeService.create(init);
//
//        //when
//        employeeService.delete(init.getId());
//
//        //then
//        assertEquals(0, employeeRepository.getEmployeeList().size());
//    }
    

}