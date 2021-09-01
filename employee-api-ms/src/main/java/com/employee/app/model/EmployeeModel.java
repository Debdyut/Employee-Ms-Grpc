package com.employee.app.model;

import java.time.LocalDate;

public class EmployeeModel {

	private String name = null;

	private LocalDate dob = null;

	private Double salary = null;

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
