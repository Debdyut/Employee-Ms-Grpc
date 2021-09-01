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

import java.time.LocalDate;

/**
 * 
 * {@code EmployeeModel} is the employee business model for saving in desired file format.
 * 
 * @author Debdyut Hajra
 *
 */
public class EmployeeModel {

	/**
	 * {@code Name} of the employee.
	 */
	private String name = null;

	/**
	 * {@code DOB} is the date of birth of the employee.
	 */
	private LocalDate dob = null;

	/**
	 * {@code Salary} of the employee in INR.
	 */
	private Double salary = null;

	/**
	 * {@code Age} of the employee in years.
	 */
	private Integer age = null;

	public EmployeeModel() {
		super();
	}

	public EmployeeModel(String name, LocalDate dob, Double salary, Integer age) {
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

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
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
		builder.append("EmployeeModel [name=").append(name).append(", dob=").append(dob).append(", salary=")
				.append(salary).append(", age=").append(age).append("]");
		return builder.toString();
	}

}
