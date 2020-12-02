package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CompanyServiceTest {
    @Test
    public void should_return_all_when_get_all_given_repository_and_companies() {
        //given
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        CompanyService companyService = new CompanyService(companyRepository);

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
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        CompanyService companyService = new CompanyService(companyRepository);

        Company expected = new Company();

        when(companyRepository.create(expected)).thenReturn(expected);

        //when
        Company actual = companyService.create(expected);

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void should_return_specified_company_when_get_given_repository_and_id() {
        //given
        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
        CompanyService companyService = new CompanyService(companyRepository);

        Company expected = new Company();

        when(companyRepository.getCompany(expected.getCompanyId())).thenReturn(expected);

        //when
        companyService.create(expected);
        Company actual = companyService.getCompany(expected.getCompanyId());

        //then
        assertEquals(expected, actual);
    }

    @Test
    public void should_return_employee_list_when_get_company_employee_given_company() {
        CompanyRepository companyRepository = new CompanyRepository();
        CompanyService companyService = new CompanyService(companyRepository);

        List<Employee> expected = new ArrayList<>();
        expected.add(new Employee());
        expected.add(new Employee());
        expected.add(new Employee());

        Company company = new Company();
        company.setCompanyId(1);
        company.setEmployees(expected);

        //when
        companyService.create(company);
        Company actual = companyService.getCompanyEmployees(company.getCompanyId());

        //then
        assertEquals(expected, actual);
    }




}