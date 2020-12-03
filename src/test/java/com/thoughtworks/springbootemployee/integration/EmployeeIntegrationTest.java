package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    public void should_return_all_employees_when_get_all_given_employees() throws Exception {
        //given
        Employee employee = new Employee("Ernest", 22, "Male", 1000);
        employeeRepository.save(employee);

        //when
        //then
        mockMvc.perform(get("/employees"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").isString())
            .andExpect(jsonPath("$[0].name").value("Ernest"))
            .andExpect(jsonPath("$[0].age").value(22))
            .andExpect(jsonPath("$[0].gender").value("Male"))
            .andExpect(jsonPath("$[0].salary").value(1000));
    }

    @Test
    public void should_return_employee_when_create_employee_given_employee() throws Exception {
        //given
        String employeeAsJson = "{\n" +
                "    \"name\": \"Ernest\",\n" +
                "    \"age\": 19,\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"salary\": 55555555\n" +
                "}";

        //when
        mockMvc.perform(post("/employees")
            .contentType(APPLICATION_JSON)
            .content(employeeAsJson))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").isString())
            .andExpect(jsonPath("$.name").value("Ernest"))
            .andExpect(jsonPath("$.age").value(19))
            .andExpect(jsonPath("$.gender").value("Male"))
            .andExpect(jsonPath("$.salary").value(55555555));

        List<Employee> employees = employeeRepository.findAll();
        //the
        assertEquals(1, employees.size());
        assertEquals("Ernest", employees.get(0).getName());
        assertEquals(19, employees.get(0).getAge());
    }
    
    @Test
    public void should_return_female_when_get_by_gender_given_employees() throws Exception {
        //given
        Employee employee1 = new Employee("Ernest", 18, "Male", 1000);
        Employee employee2 = new Employee("Ernest2", 19, "Female", 1000);
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        //when
        mockMvc.perform(get("/employees").param("gender", "Female"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].gender").value("Female"));

    }

    @Test
    public void should_return_two_employees_when_get_all_with_page_size_given_employees() throws Exception {
        //given
        Employee employee1 = new Employee("Ernest", 18, "Male", 1000);
        Employee employee2 = new Employee("Ernest2", 19, "Female", 1000);
        Employee employee3 = new Employee("Ernest3", 18, "Male", 1000);
        Employee employee4 = new Employee("Ernest4", 19, "Female", 1000);
        employeeRepository.saveAll(Arrays.asList(employee1, employee2, employee3, employee4));

        //when
        mockMvc.perform(get("/employees?page=2&pageSize=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Ernest3"))
                .andExpect(jsonPath("$", hasSize(2)));
    }


    @Test
    public void should_return_not_found_status_when_get_employee_given_invalid_id() throws Exception {
        //when
        mockMvc.perform(get("/employees/123"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_return_correct_employee_when_get_employee_given_valid_id() throws Exception {
        //given
        Employee employee = new Employee("Ernest", 18, "Male", 1000);
        employeeRepository.save(employee);

        //when
        mockMvc.perform(get("/employees/"+employee.getId()))
                    .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.name").value("Ernest"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andExpect(jsonPath("$.salary").value(1000));
    }

}
