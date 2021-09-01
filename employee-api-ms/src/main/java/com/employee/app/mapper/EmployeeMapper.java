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

package com.employee.app.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.employee.app.grpc.EmployeeBase;
import com.employee.app.model.EmployeeForXml;
import com.employee.app.model.EmployeeListForXml;
import com.employee.app.model.EmployeeModel;

/**
 * 
 * {@code EmployeeMapper} helps to transform employee models from one form to other.
 * 
 * @author Debdyut Hajra
 *
 */
@Component
public class EmployeeMapper {

	/**
	 * {@code StringEncryptor} helps to encrypt or decrypt the strings.
	 */
	private StringEncryptor jasyptStringEncryptor;

	/**
	 * {@code DateTimeFormatter} helps to parse or format dates.
	 */
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d-M-yyyy");
	
	/**
	 * Autowire the dependencies through constructor injection.
	 * 
	 * @param jasyptStringEncryptor
	 */
	public EmployeeMapper (final StringEncryptor jasyptStringEncryptor) {
		this.jasyptStringEncryptor = jasyptStringEncryptor;
	}

	/**
	 * 
	 * Convert from google protobuff model to business model
	 * 
	 * @param source
	 * @return {@code com.employee.app.model.EmployeeModel}
	 */
	public EmployeeModel protoToModel(EmployeeBase source) {
		// Check if source object is empty
		if (ObjectUtils.isEmpty(source)) {
			return null;
		}
		
		// Build the target model using the source model
		var target = new EmployeeModel();
		target.setName(jasyptStringEncryptor.decrypt(source.getName()));
		target.setDob(fromProtoDate(jasyptStringEncryptor.decrypt(source.getDob())));
		target.setSalary(Double.parseDouble(jasyptStringEncryptor.decrypt(source.getSalary())));
		target.setAge(Integer.parseInt(jasyptStringEncryptor.decrypt(source.getAge())));
		
		// Return the target model
		return target;
	}

	/**
	 * 
	 * Convert from list of google protobuff models to list of business models
	 * 
	 * @param source
	 * @return {@code java.util.List<com.employee.app.model.EmployeeModel>}
	 */
	public List<EmployeeModel> protoToModel(List<EmployeeBase> source) {
		// Check if source collection is empty
		if (CollectionUtils.isEmpty(source)) {
			return new ArrayList<>();
		}
		// Transform the source model and return
		return source.stream().map(this::protoToModel).collect(Collectors.toList());
	}

	/**
	 * 
	 * Convert from business model to jaxb model for storing or reading from
	 * XML files.
	 * 
	 * @param source
	 * @return {@code com.employee.app.model.EmployeeForXml}
	 */
	public EmployeeForXml modelToJaxbModel(EmployeeModel source) {
		// Check if source object is empty
		if (ObjectUtils.isEmpty(source)) {
			return null;
		}
		
		// Build the target model using the source model
		var target = new EmployeeForXml();
		target.setName(source.getName());
		if (!ObjectUtils.isEmpty(source.getDob())) {
			target.setDob(source.getDob().format(dateTimeFormatter));
		}
		target.setSalary(source.getSalary());
		target.setAge(source.getAge());
		
		// Return the target model
		return target;
	}

	/**
	 * 
	 * Convert from array of business models to list of jaxb model for storing or reading from
	 * XML files.
	 * 
	 * @param source
	 * @return {@code com.employee.app.model.EmployeeListForXml}
	 */
	public EmployeeListForXml modelToJaxbModel(EmployeeModel[] source) {
		// Check if source array is empty
		if (ArrayUtils.isEmpty(source)) {
			return null;
		}
		
		// Build the target model using the source model
		var target = new EmployeeListForXml();
		for (var employeeModel : source) {
			target.addEmployee(modelToJaxbModel(employeeModel));
		}
		
		// Return the target model
		return target;
	}

	/**
	 * 
	 * Parse the date string.
	 * 
	 * @param source
	 * @return {@code java.time.LocalDate}
	 */
	private LocalDate fromProtoDate(String source) {
		return LocalDate.parse(source, dateTimeFormatter);
	}

}
