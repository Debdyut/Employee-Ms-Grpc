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

import org.apache.commons.lang3.ObjectUtils;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Component;

import com.employee.app.grpc.EmployeeBase;

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
	private final StringEncryptor jasyptStringEncryptor;
	
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
	 * Convert from dto model to google protobuff model
	 * 
	 * @param source
	 * @return {@code com.employee.v1.EmployeeBase}
	 */
	public EmployeeBase dtoToProto(com.employee.app.dto.Employee source ) {
		// Check if source object is empty
		if (ObjectUtils.isEmpty(source)) {
			return null;
		}
		
		// Build the target model using the source model
		var target = EmployeeBase.newBuilder().setName(jasyptStringEncryptor.encrypt(source.getName()))
									.setDob(jasyptStringEncryptor.encrypt(source.getDob()))
									.setSalary(jasyptStringEncryptor.encrypt(Double.toString(source.getSalary())))									
									.setAge(jasyptStringEncryptor.encrypt(Integer.toString(source.getAge())))
									.build();
		// Return the target model
		return target;
	}
	
}
