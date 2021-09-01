/*
 * Copyright 2021-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.employee.app.api.controller;

import javax.validation.Valid;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.app.api.EmployeeAPI;
import com.employee.app.dto.request.EmployeeRequest;
import com.employee.app.dto.response.EmployeeResponse;
import com.employee.app.service.IEmployeeService;

/**
 * {@code EmployeeController} provides implementations for Employee APIs.
 * 
 * @author Debdyut Hajra
 *
 */
@RestController
@RequestMapping("v1")
public class EmployeeController 
	implements EmployeeAPI 
{
	/**
	 * {@code EmployeeService} provides the functionalities for storing,
	 * updating and retrieving files.
	 */
	private final IEmployeeService employeeService; 
	
	/**
	 * Autowire the dependencies through constructor injection.
	 * 
	 * @param properties
	 */
	public EmployeeController(final IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<?> store(
			@RequestHeader(value = "fileType", required = true) String fileType,			 
			@Valid @RequestBody EmployeeRequest body,
			@RequestHeader(value = "fileName", required = false) String fileName) 
	{	
		// Store data to file
		var response = employeeService.store(fileType, body, fileName);

		// Return reponse
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<EmployeeResponse> update(
			@RequestHeader(value = "fileType", required = true)  String fileType, 
			@RequestHeader(value = "fileName", required = true) String fileName, 
			@Valid @RequestBody EmployeeRequest body) 
	{
		// Update file contents
		var response = employeeService.update(fileType, body, fileName);
		
		// Return reponse
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<?> read(
			@PathVariable("filename") String filename, 
			@Valid @RequestParam(value = "download", required = false, defaultValue = "false") Boolean download) 
	{
		// Retrieve file content and send as resource or string, based on download option 
		if (download == true) {
			Resource resource = employeeService.readAsResource(filename);
			return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION,
	                        "attachment; filename=\"" + filename + "\"")
	                .body(resource);
		} else {
			String fileContent = employeeService.readFile(filename);
			return ResponseEntity.ok().body(fileContent);
		}		
	}
	
}
