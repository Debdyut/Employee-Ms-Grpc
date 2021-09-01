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

package com.employee.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * {@code EmployeeListForXml} contains list of employee models for saving as XML.
 * 
 * @author Debdyut Hajra
 *
 */
@XmlRootElement(name = "employees")
@XmlAccessorType (XmlAccessType.FIELD)
public class EmployeeListForXml {

	/**
	 * {@code Employees} is the list of employees to be saved as XML. 
	 */
	@XmlElement(name = "employee")
	private List<EmployeeForXml> employees = new ArrayList<>();

	public EmployeeListForXml() {
		super();
	}

	public EmployeeListForXml(List<EmployeeForXml> employees) {
		super();
		this.employees = employees;
	}

	public List<EmployeeForXml> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeForXml> employees) {
		this.employees = employees;
	}
	
	public void addEmployee(EmployeeForXml employee) {
		this.employees.add(employee);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeListForXml [employees=").append(employees).append("]");
		return builder.toString();
	}

}
