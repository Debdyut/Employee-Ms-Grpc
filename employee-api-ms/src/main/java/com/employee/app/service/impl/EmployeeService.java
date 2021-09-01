package com.employee.app.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import com.employee.app.exception.FileNotFoundException;
import com.employee.app.grpc.EmployeeRequest;
import com.employee.app.grpc.EmployeeRequest.FileFormat;
import com.employee.app.grpc.EmployeeServiceGrpc.EmployeeServiceImplBase;
import com.employee.app.helper.EmployeeCsvHelper;
import com.employee.app.helper.EmployeeXmlHelper;
import com.employee.app.mapper.EmployeeMapper;
import com.employee.app.model.EmployeeModel;
import com.employee.app.service.IStorageService;

import io.grpc.stub.StreamObserver;

@Service
public class EmployeeService extends EmployeeServiceImplBase {

	@Autowired
	private IStorageService storageService;
	
	@Autowired
	private EmployeeCsvHelper csvHelper;
	
	@Autowired
	private EmployeeXmlHelper xmlHelper;
	
	@Autowired
	private EmployeeMapper employeeMapper;

	@Override
	public void create(EmployeeRequest req, StreamObserver<EmployeeRequest.Response> resObserver) {

		EmployeeModel[] employeeArr = employeeMapper.protoToModel(req.getEmployeeList()).toArray(EmployeeModel[]::new);

		String fileName = null;
		if (!StringUtils.isEmpty(req.getName())) {
			fileName = req.getName();
		}
		else {
			fileName = UUID.randomUUID().toString();
		}

		File file = null;
		if (FileFormat.CSV.equals(req.getFileType())) {
			file = csvHelper.store(fileName, employeeArr);
		} else if (FileFormat.XML.equals(req.getFileType())) {
			file = xmlHelper.store(fileName, employeeArr);
		}

		EmployeeRequest.Response resp = EmployeeRequest.Response.newBuilder()
				.setName(fileName)
				.setType(req.getFileType().name())
				.setSize(Long.toString(file.length()))
				.setRecordsNum(Integer.toString(req.getEmployeeList().size()))
				.build();
		
		resObserver.onNext(resp);
		resObserver.onCompleted();
	}
	
	@Override
	public void update(EmployeeRequest req, StreamObserver<EmployeeRequest.Response> resObserver) {

		EmployeeModel[] employeeArr = employeeMapper.protoToModel(req.getEmployeeList()).toArray(EmployeeModel[]::new);

		var fileName = req.getName() + "." + req.getFileType().name().toLowerCase();
		
		var path = storageService.load(fileName);
		
		if (ObjectUtils.isEmpty(path)) {
			throw new FileNotFoundException("File not found: " + fileName);
		}
		
		File file = null;
		if (FileFormat.CSV.equals(req.getFileType())) {
			file = csvHelper.store(fileName, employeeArr);
		} else if (FileFormat.XML.equals(req.getFileType())) {
			file = xmlHelper.store(fileName, employeeArr);
		}

		EmployeeRequest.Response resp = EmployeeRequest.Response.newBuilder()
				.setName(fileName)
				.setType(req.getFileType().name())
				.setSize(Long.toString(file.length()))
				.setRecordsNum(Integer.toString(req.getEmployeeList().size()))
				.build();
		
		resObserver.onNext(resp);
		resObserver.onCompleted();
	}

}
