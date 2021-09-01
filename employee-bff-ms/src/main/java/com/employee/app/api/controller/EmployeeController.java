package com.employee.app.api.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("v1")
public class EmployeeController 
	implements EmployeeAPI 
{
	private Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IEmployeeService employeeService; 

	@Override
	public ResponseEntity<?> store(
			@RequestHeader(value = "fileType", required = true) String fileType,			 
			@Valid @RequestBody EmployeeRequest body,
			@RequestHeader(value = "fileName", required = false) String fileName) {
		
		var response = employeeService.store(fileType, body, fileName);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@Override
	public ResponseEntity<EmployeeResponse> update(
			@RequestHeader(value = "fileType", required = true)  String fileType, 
			@RequestHeader(value = "fileName", required = true) String fileName, 
			@Valid @RequestBody EmployeeRequest body) {
		var response = employeeService.update(fileType, body, fileName);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@Override
	public ResponseEntity<?> read(
			@PathVariable("filename") String filename, 
			@Valid @RequestParam(value = "download", required = false, defaultValue = "false") Boolean download) {
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
