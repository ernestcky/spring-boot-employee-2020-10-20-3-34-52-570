package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {
    @InjectMocks
    CompanyService companyService;

    @Mock
    CompanyRepository companyRepository;

    @Mock
    EmployeeRepository employeeRepository;

    @Test
    public void should_return_all_when_get_all_given_repository_and_companies() {
        //given
        List<Company> expected = Arrays.asList(new Company(), new Company());
        when(companyRepository.findAll()).thenReturn(expected);

        //when
        List<Company> actual = companyService.findAll();

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void should_return_created_company_when_create_given_repository() {
        //given
        Company expected = new Company();

        when(companyRepository.insert(expected)).thenReturn(expected);

        //when
        Company actual = companyService.create(expected);

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void should_return_specified_company_when_get_given_repository_and_id() {
        //given
        Company expected = new Company();
        expected.setCompanyId("1");

        when(companyRepository.findById(expected.getCompanyId())).thenReturn(Optional.of(expected));

        //when
        Company actual = companyService.getCompany(expected.getCompanyId());

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void should_return_employee_list_when_get_company_employee_given_company() {
        List<Employee> expected = new ArrayList<>();
        expected.add(new Employee());
        expected.add(new Employee());
        expected.add(new Employee());

        Company company = new Company();

        when(employeeRepository.findAllByCompanyId(company.getCompanyId())).thenReturn(expected);
        when(companyRepository.existsById(company.getCompanyId())).thenReturn(true);

        //when
        companyService.create(company);
        List<Employee> actual = companyService.getCompanyEmployees(company.getCompanyId());

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void should_return_two_companies_when_get_all_with_page_size_given_repository() {
        //given
        List<Company> expected = Arrays.asList(
                new Company("test", 3),
                new Company("test2", 30)
        );

        when(companyRepository.findAll((Pageable)any())).thenReturn(new PageImpl<>(expected));

        //when
        List<Company> actual = companyService.findAll(2, 2).toList();

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void should_return_correct_company_details_when_update_given_repository_and_employee() {
        //given
        Company init = new Company("init", 30);
        Company expected = new Company("expected", 20);

        when(companyRepository.existsById(init.getCompanyId())).thenReturn(true);
        when(companyRepository.save(expected)).thenReturn(expected);

        //when
        Company actual = companyService.update(init.getCompanyId(), expected);

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void should_delete_successfully_when_delete_given_repository_and_company_list() {
        //given
        Integer expected = companyService.findAll().size();
        Company init = new Company();
        companyRepository.insert(init);

        //when
        companyService.delete(init.getCompanyId());

        //then
        assertEquals(expected, companyRepository.findAll().size());
    }
}