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

package com.employee.app.service.impl;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.employee.app.client.GrpcClient;
import com.employee.app.dto.request.EmployeeRequest;
import com.employee.app.dto.response.EmployeeResponse;
import com.employee.app.grpc.EmployeeRequest.FileFormat;
import com.employee.app.mapper.EmployeeMapper;
import com.employee.app.service.IEmployeeService;

import reactor.core.publisher.Mono;

/**
 * {@code EmployeeService} provides the implementations for the core functionalities of the
 * Employee service.
 * 
 * @author Debdyut Hajra
 *
 */
@Service
public class EmployeeService implements IEmployeeService {
	
	@Value("${employee.api.url:http://localhost:9090}")
	private String employeeApiUrl;

	/**
	 * {@code GrpcClient} helps to make requests to the grpc server.
	 */
	private final GrpcClient client;

	/**
	 * {@code EmployeeMapper} helps to transform employee models.
	 */
	private final EmployeeMapper employeeMapper;
	
	/**
	 * {@code StringEncryptor} helps to encrypt or decrypt the strings.
	 */
	private final StringEncryptor jasyptStringEncryptor;
	
	/**
	 * Autowire the dependencies through constructor injection.
	 * 
	 * @param client
	 * @param employeeMapper
	 * @param jasyptStringEncryptor
	 */
	public EmployeeService (
			final GrpcClient client, 
			final EmployeeMapper employeeMapper, 
			final StringEncryptor jasyptStringEncryptor) 
	{
		this.client = client;
		this.employeeMapper = employeeMapper;
		this.jasyptStringEncryptor = jasyptStringEncryptor;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmployeeResponse store(
			final String fileType, 
			final EmployeeRequest employeeReq, 
			final String fileName) 
	{
		// Initialize request
		var req = com.employee.app.grpc.EmployeeRequest.newBuilder().setName(fileName)
				.setFileType(FileFormat.valueOf(fileType));

		// Convert from dto model to google protobuff model
		var employeeList = employeeReq.getEmployees().stream().map(employeeMapper::dtoToProto)
				.collect(Collectors.toList());

		// Add employee data to request
		req.addAllEmployee(employeeList);

		// Call the store grpc endpoint
		var response = client.getSourceServiceStub().create(req.build());

		// Build response
		var employeeResponse = new EmployeeResponse();
		employeeResponse.setFileName(response.getName());
		employeeResponse.setFileSize(Double.parseDouble(response.getSize()));
		employeeResponse.setFileType(response.getType());
		employeeResponse.setRecordsNum(new BigDecimal(response.getRecordsNum()));

		// Return the response
		return employeeResponse;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EmployeeResponse update(
			final String fileType, 
			final EmployeeRequest employeeReq, 
			final String fileName) 
	{
		// Initialize request
		var req = com.employee.app.grpc.EmployeeRequest.newBuilder().setName(fileName)
				.setFileType(FileFormat.valueOf(fileType));

		// Convert from dto model to google protobuff model
		var employeeList = employeeReq.getEmployees().stream().map(employeeMapper::dtoToProto)
				.collect(Collectors.toList());

		// Add employee data to request
		req.addAllEmployee(employeeList);

		// Call the store grpc endpoint
		var response = client.getSourceServiceStub().update(req.build());

		// Build response
		var employeeResponse = new EmployeeResponse();
		employeeResponse.setFileName(response.getName());
		employeeResponse.setFileType(response.getType());
		employeeResponse.setFileSize(Double.parseDouble(response.getSize()));
		employeeResponse.setRecordsNum(new BigDecimal(response.getRecordsNum()));

		// Return the response
		return employeeResponse;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String readFile(String fileName) {
		// Initialize web client
		WebClient client = WebClient.builder()
				  .baseUrl(employeeApiUrl)				  
				  .build();
		
		// Call the retrieve file endpoint
		String fileContent = client.get().uri("/download/" + fileName)
								.retrieve()
								.onStatus(HttpStatus::is4xxClientError,
										error -> Mono.error(new RuntimeException("Bad Request")))
								.onStatus(HttpStatus::is5xxServerError,
										error -> Mono.error(new RuntimeException("Server is not responding")))
								.bodyToMono(String.class)
								.block();
		
		// Decrypt the file contents
		fileContent = jasyptStringEncryptor.decrypt(fileContent);
		
		// Send the decrypted file contents
		return fileContent;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Resource readAsResource(String fileName) {
		// Retrieve file contents
		String fileContent = readFile(fileName);
		
		// Build resource object from string
		Resource resource = new ByteArrayResource(fileContent.getBytes());
		
		// Return resource response
		return resource;
	}

}
