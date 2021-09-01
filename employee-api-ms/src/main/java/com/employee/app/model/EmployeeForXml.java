/*
 * Copyright 2002-2021 the original author or authors.
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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.employee.app.config.MoneySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * {@code EmployeeForXml} is the employee model for saving as XML.
 * 
 * @author Debdyut Hajra
 *
 */
@XmlRootElement(name = "employee")
@XmlAccessorType (XmlAccessType.FIELD)
public class EmployeeForXml {

	/**
	 * {@code Name} of the employee.
	 */
	@XmlAttribute(name = "name")
	private String name = null;

	/**
	 * {@code DOB} is the date of birth of the employee.
	 */
	@XmlAttribute(name = "dob")
	private String dob = null;

	/**
	 * {@code Salary} of the employee in INR.
	 */
	@XmlAttribute(name = "salary")
	@JsonSerialize(using = MoneySerializer.class)
	private Double salary = null;

	/**
	 * {@code Age} of the employee in years.
	 */
	@XmlAttribute(name = "age")
	private Integer age = null;

	public EmployeeForXml() {
		super();
	}

	public EmployeeForXml(String name, String dob, Double salary, Integer age) {
		super();
		this.name = name;
		this.dob = dob;
		this.salary = salary;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeForXml [name=").append(name).append(", dob=").append(dob).append(", salary=")
				.append(salary).append(", age=").append(age).append("]");
		return builder.toString();
	}

}
