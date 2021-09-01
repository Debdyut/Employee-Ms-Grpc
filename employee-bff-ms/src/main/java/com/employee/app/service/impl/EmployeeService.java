package com.employee.app.service.impl;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import org.jasypt.encryption.StringEncryptor;
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

@Service
public class EmployeeService implements IEmployeeService {

	private final GrpcClient client;

	private final EmployeeMapper employeeMapper;
	
	private final StringEncryptor jasyptStringEncryptor;
	
	public EmployeeService (final GrpcClient client, final EmployeeMapper employeeMapper, final StringEncryptor jasyptStringEncryptor) {
		this.client = client;
		this.employeeMapper = employeeMapper;
		this.jasyptStringEncryptor = jasyptStringEncryptor;
	}

	@Override
	public EmployeeResponse store(String fileType, EmployeeRequest employeeReq, String fileName) {

		var req = com.employee.app.grpc.EmployeeRequest.newBuilder().setName(fileName)
				.setFileType(FileFormat.valueOf(fileType));

		var employeeList = employeeReq.getEmployees().stream().map(employeeMapper::dtoToProto)
				.collect(Collectors.toList());

		req.addAllEmployee(employeeList);

		var response = client.getSourceServiceStub().create(req.build());

		var employeeResponse = new EmployeeResponse();
		employeeResponse.setFileName(response.getName());
		employeeResponse.setFileSize(Double.parseDouble(response.getSize()));
		employeeResponse.setRecordsNum(new BigDecimal(response.getRecordsNum()));

		return employeeResponse;
	}

	@Override
	public EmployeeResponse update(String fileType, EmployeeRequest employeeReq, String fileName) {
		var req = com.employee.app.grpc.EmployeeRequest.newBuilder().setName(fileName)
				.setFileType(FileFormat.valueOf(fileType));

		var employeeList = employeeReq.getEmployees().stream().map(employeeMapper::dtoToProto)
				.collect(Collectors.toList());

		req.addAllEmployee(employeeList);

		var response = client.getSourceServiceStub().update(req.build());

		var employeeResponse = new EmployeeResponse();
		employeeResponse.setFileName(response.getName());
		employeeResponse.setFileSize(Double.parseDouble(response.getSize()));
		employeeResponse.setRecordsNum(new BigDecimal(response.getRecordsNum()));

		return employeeResponse;
	}

	@Override
	public String readFile(String fileName) {
		WebClient client = WebClient.builder()
				  .baseUrl("http://localhost:9090")				  
				  .build();
		
		String fileContent = client.get().uri("/download/" + fileName)
								.retrieve()
								.onStatus(HttpStatus::is4xxClientError,
										error -> Mono.error(new RuntimeException("API not found")))
								.onStatus(HttpStatus::is5xxServerError,
										error -> Mono.error(new RuntimeException("Server is not responding")))
								.bodyToMono(String.class)
								.block();
		fileContent = jasyptStringEncryptor.decrypt(fileContent);
		
		return fileContent;
	}
	
	@Override
	public Resource readAsResource(String fileName) {		
		String fileContent = readFile(fileName);
		Resource resource = new ByteArrayResource(fileContent.getBytes());
		return resource;
	}

}
