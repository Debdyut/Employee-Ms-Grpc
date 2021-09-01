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

package com.employee.app.service;

import org.springframework.core.io.Resource;

import com.employee.app.dto.request.EmployeeRequest;
import com.employee.app.dto.response.EmployeeResponse;

/**
 * {@code IEmployeeService} defines the core functionalities of the employee service.
 * 
 * @author Debdyut Hajra
 *
 */
public interface IEmployeeService {

	/**
	 * {@code Store} method stores the data as CSV or XML file. 
	 * 
	 * @param fileType
	 * @param body
	 * @param fileName
	 * @return
	 */
	EmployeeResponse store(String fileType, EmployeeRequest body, String fileName);

	/**
	 * {@code Update} method updates an existing file.
	 * 
	 * @param fileType
	 * @param body
	 * @param fileName
	 * @return
	 */
	EmployeeResponse update(String fileType, EmployeeRequest body, String fileName);

	/**
	 * {@code ReadFile} method reads an existing file and returns file contents as string. 
	 * 
	 * @param filename
	 * @return
	 */
	String readFile(String filename);

	/**
	 * {@code ReadAsResource} method reads an existing file and returns file contents as resource.
	 * 
	 * @param fileName
	 * @return
	 */
	Resource readAsResource(String fileName);

}
