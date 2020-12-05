package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        List<Employee> actual = employeeService.findAll();

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

    @Test
    public void should_return_female_employ_when_get_by_gender_given_param_is_female() {
        //given
        Employee male1 = new Employee(null, null, null, "M", null);
        Employee female1 = new Employee(null, null, null, "F", null);
        Employee male2 = new Employee(null, null, null, "M", null);

        List<Employee> init = Arrays.asList(male1, female1, male2);
        List<Employee> expected = Arrays.asList(female1);

        when(employeeRepository.findAllByGender("F")).thenReturn(expected);

        //when
        List<Employee> actual = this.employeeService.findAll("F");

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void should_return_two_employee_when_get_all_with_page_size_given_repository() {
        //given
        List<Employee> expected = Arrays.asList(
                new Employee("3", null, null, "F", null),
                new Employee("4", null, null, "F", null)
        );

        when(employeeRepository.findAll((Pageable)any())).thenReturn(new PageImpl<>(expected));

        //when
        Page<Employee> actual = employeeService.findAll(2, 2);

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void should_delete_successfully_when_delete_given_repository_and_employee_list() {
        //given
        String id = "1";

        //when
        employeeService.delete(id);

        //then
        verify(employeeRepository, times(1)).deleteById("1");
    }


}