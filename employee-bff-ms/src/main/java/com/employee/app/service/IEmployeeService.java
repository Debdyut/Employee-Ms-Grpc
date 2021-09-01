package com.employee.app.service;

import org.springframework.core.io.Resource;

import com.employee.app.dto.request.EmployeeRequest;
import com.employee.app.dto.response.EmployeeResponse;

public interface IEmployeeService {

	EmployeeResponse store(String fileType, EmployeeRequest body, String fileName);

	EmployeeResponse update(String fileType, EmployeeRequest body, String fileName);

	String readFile(String filename);

	Resource readAsResource(String fileName);

}
