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

import static com.employee.app.constant.GenericConstants.CSV_EXTENSION;
import static com.employee.app.constant.GenericConstants.XML_EXTENSION;

import java.io.File;
import java.util.UUID;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.app.exception.ExceptionUtils;
import com.employee.app.exception.FileNotFoundException;
import com.employee.app.exception.StorageException;
import com.employee.app.grpc.EmployeeRequest;
import com.employee.app.grpc.EmployeeRequest.FileFormat;
import com.employee.app.grpc.EmployeeServiceGrpc.EmployeeServiceImplBase;
import com.employee.app.helper.EmployeeCsvHelper;
import com.employee.app.helper.EmployeeXmlHelper;
import com.employee.app.mapper.EmployeeMapper;
import com.employee.app.model.EmployeeModel;
import com.employee.app.service.IStorageService;

import io.grpc.stub.StreamObserver;

/**
 * {@code EmployeeService} provides the implementations for grpc service interface.
 * 
 * @author Debdyut Hajra
 *
 */
@Service
public class EmployeeService extends EmployeeServiceImplBase {

	/**
	 * {@code StorageService} provides basic implementations for
	 * core storage functionalities.
	 */
	@Autowired
	private IStorageService storageService;
	
	/**
	 * {@code EmployeeCsvHelper} provides functionality store and update
	 * CSV files.
	 */
	@Autowired
	private EmployeeCsvHelper csvHelper;
	
	/**
	 * {@code EmployeeXmlHelper} provides functionality store and update
	 * XML files.
	 */
	@Autowired
	private EmployeeXmlHelper xmlHelper;
	
	/**
	 * {@code EmployeeMapper} helps to transform employee models.
	 */
	@Autowired
	private EmployeeMapper employeeMapper;

	/**
	 * {@code Create} method helps to store the files in CSV and XML formats. 
	 */
	@Override
	public void create(EmployeeRequest req, StreamObserver<EmployeeRequest.Response> resObserver) {

		try {
			// Transform request to employee business model
			EmployeeModel[] employeeArr = employeeMapper.protoToModel(req.getEmployeeList()).toArray(EmployeeModel[]::new);
	
			// Retrieve file name from request.
			// If file name is not available, set a random UUID as file name.
			String fileName = null;
			if (!StringUtils.isEmpty(req.getName())) {
				fileName = req.getName();
			}
			else {
				fileName = UUID.randomUUID().toString();
			}
	
			// Store the file in desired file format
			File file = null;
			if (FileFormat.CSV.equals(req.getFileType())) {
				file = csvHelper.store(fileName, employeeArr);
			} else if (FileFormat.XML.equals(req.getFileType())) {
				file = xmlHelper.store(fileName, employeeArr);
			}			
			
			// Build response
			EmployeeRequest.Response resp = EmployeeRequest.Response.newBuilder()
					.setName(fileName)
					.setType(req.getFileType().name())
					.setSize(Long.toString(file.length()))
					.setRecordsNum(Integer.toString(req.getEmployeeList().size()))
					.build();
			
			resObserver.onNext(resp);
			resObserver.onCompleted();
		} catch (Exception e) {
			ExceptionUtils.observeError(resObserver, e, EmployeeRequest.Response.getDefaultInstance());
		}
	}
	
	/**
	 * {@code Update} method helps to update existing files. 
	 */
	@Override
	public void update(EmployeeRequest req, StreamObserver<EmployeeRequest.Response> resObserver) {

		try {
			// Transform request to employee business model
			EmployeeModel[] employeeArr = employeeMapper.protoToModel(req.getEmployeeList()).toArray(EmployeeModel[]::new);
	
			// Retrieve file name from request
			String fileName = req.getName();
			if (!fileName.endsWith(CSV_EXTENSION) || !fileName.endsWith(XML_EXTENSION)) {
				fileName += "." + req.getFileType().name().toLowerCase();
			}
			
			// Retrieve path using file name
			var path = storageService.load(fileName);
			
			// Throw error if file not found
			if (!path.toFile().exists()) {
				throw new FileNotFoundException("File not found: " + fileName);				
			}
			
			// Update file content
			File file = null;
			if (FileFormat.CSV.equals(req.getFileType())) {
				file = csvHelper.update(fileName, employeeArr);
			} else if (FileFormat.XML.equals(req.getFileType())) {
				file = xmlHelper.update(fileName, employeeArr);
			}
	
			// Build response
			EmployeeRequest.Response resp = EmployeeRequest.Response.newBuilder()
					.setName(fileName)
					.setType(req.getFileType().name())
					.setSize(Long.toString(file.length()))
					.setRecordsNum(Integer.toString(req.getEmployeeList().size()))
					.build();
			
			resObserver.onNext(resp);
			resObserver.onCompleted();
		} catch (Exception e) {
			ExceptionUtils.observeError(resObserver, e, EmployeeRequest.Response.getDefaultInstance());
		}
	}

}
