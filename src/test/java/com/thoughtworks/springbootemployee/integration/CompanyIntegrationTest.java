package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;

    @AfterEach
    void tearDown() {
        companyRepository.deleteAll();
    }

    @Test
    public void should_return_all_companies_when_get_all_given_companies() throws Exception {
        //given
        Company company = new Company("Company1", 100);
        companyRepository.insert(company);

        //when
        mockMvc.perform(get("/companies"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$[0].companyId").isString())
                        .andExpect(jsonPath("$[0].companyName").value("Company1"))
                        .andExpect(jsonPath("$[0].employeesNumber").value(100));
    }

    @Test
    public void should_return_company_when_create_company_given_company() throws Exception {
        //given
        String employeeAsJson = "{\n" +
                "    \"companyName\": \"company1\",\n" +
                "    \"employeesNumber\": 200\n" +
                "}";

        //when
        mockMvc.perform(post("/companies")
                .contentType(APPLICATION_JSON)
                .content(employeeAsJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyId").isString())
                .andExpect(jsonPath("$.companyName").value("company1"))
                .andExpect(jsonPath("$.employeesNumber").value(200));

        List<Company> companies = companyRepository.findAll();
        //the
        assertEquals(1, companies.size());
        assertEquals("company1", companies.get(0).getCompanyName());
        assertEquals(200, companies.get(0).getEmployeesNumber());
    }


}
