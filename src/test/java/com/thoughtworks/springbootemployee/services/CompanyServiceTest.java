package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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



}