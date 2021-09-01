package com.employee.app.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.employee.app.service.IEmployeeService;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

	@MockBean
	private IEmployeeService employeeService; 
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void create_success() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders
		        .post("/v1/employees")
		        .accept(MediaType.APPLICATION_JSON)
		        .header("fileType", "CSV")
		        .header("fileName", "employee-oct-2021") // optional field
		        .content("{\"employees\": [{\"name\": \"Sachin Tendulkar\", \"dob\": \"20-08-1950\", \"salary\": 122111241.5, \"age\": 56}, {\"name\": \"Saurav Ganguly\", \"dob\": \"9-6-1986\", \"salary\": 100011241.5, \"age\": 40}]}")
		        .contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
		        .andExpect(status().isCreated()).andReturn();
		assertNotNull(result);
	}
	
	@Test
	public void update_success() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders
		        .put("/v1/employees")
		        .accept(MediaType.APPLICATION_JSON)
		        .header("fileType", "CSV")
		        .header("fileName", "employee-oct-2021") // optional field
		        .content("{\"employees\": [{\"name\": \"Sachin Tendulkar\", \"dob\": \"20-08-1950\", \"salary\": 122111241.5, \"age\": 56}, {\"name\": \"Saurav Ganguly\", \"dob\": \"9-6-1986\", \"salary\": 100011241.5, \"age\": 40}]}")
		        .contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
		        .andExpect(status().isOk()).andReturn();
		assertNotNull(result);
	}
	
	@Test
	public void read_success() throws Exception {
		
		RequestBuilder request = MockMvcRequestBuilders
		        .get("/v1/employees/employee-sep-2021.xml")
		        .accept(MediaType.TEXT_PLAIN)
		        .queryParam("download", "false");
		
		MvcResult result = mockMvc.perform(request)
		        .andExpect(status().isOk()).andReturn();
		assertNotNull(result);
	}
	
}
