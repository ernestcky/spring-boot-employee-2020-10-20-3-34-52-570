package com.thoughtworks.springbootemployee.services;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {
    @InjectMocks
    CompanyService companyService;

    @Mock
    CompanyRepository companyRepository;

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

//    @Test
//    public void should_return_specified_company_when_get_given_repository_and_id() {
//        //given
//        CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
//        CompanyService companyService = new CompanyService(companyRepository);
//
//        Company expected = new Company();
//
//        when(companyRepository.getCompany(expected.getCompanyId())).thenReturn(expected);
//
//        //when
//        Company actual = companyService.getCompany(expected.getCompanyId());
//
//        //then
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void should_return_employee_list_when_get_company_employee_given_company() {
//        CompanyRepository companyRepository = new CompanyRepository();
//        CompanyService companyService = new CompanyService(companyRepository);
//
//        List<Employee> expected = new ArrayList<>();
//        expected.add(new Employee());
//        expected.add(new Employee());
//        expected.add(new Employee());
//
//        Company company = new Company();
//        company.setCompanyId(1);
//        company.setEmployees(expected);
//
//        //when //todo: repo, go to given
//        companyService.create(company);
//        List<Employee> actual = companyService.getCompanyEmployees(company.getCompanyId());
//
//        //then
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void should_return_two_companies_when_get_all_with_page_size_given_repository() {
//        //given
//        CompanyRepository companyRepository = new CompanyRepository();
//        CompanyService companyService = new CompanyService(companyRepository);
//        List<Company> init = Arrays.asList(
//                new Company(1, null, null, null),
//                new Company(2, null, null, null),
//                new Company(3, null, null, null),
//                new Company(4, null, null, null),
//                new Company(5, null, null, null)
//        );
//
//        List<Company> expected = init.stream().filter(employee -> employee.getCompanyId().equals(3) || employee.getCompanyId().equals(4)).collect(Collectors.toList());
//
//        //when
//        init.stream().forEach(company -> companyService.create(company));
//        List<Company> actual = companyService.findAll(2, 2);
//
//        //then
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void should_return_correct_company_details_when_update_given_repository_and_employee() {
//        //given
//        CompanyRepository companyRepository = new CompanyRepository();
//        CompanyService companyService = new CompanyService(companyRepository);
//        Company init = new Company(1, "ABC Company", 22, new ArrayList<>());
//
//        Company expected = new Company(1, "XYZ Company", 22, new ArrayList<>());
//
//        //when
//        Company actual = companyService.update(init.getCompanyId(), expected);
//
//        //then
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void should_delete_successfully_when_delete_given_repository_and_company_list() {
//        //given
//        CompanyRepository companyRepository = new CompanyRepository();
//        CompanyService companyService = new CompanyService(companyRepository);
//        Company init = new Company();
//        init.setCompanyId(1);
//        companyService.create(init);
//
//        //when
//        companyService.delete(init.getCompanyId());
//
//        //then
//        assertEquals(0, companyRepository.getCompanyList().size());
//    }

}