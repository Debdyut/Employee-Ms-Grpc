package com.employee.app.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.employee.app.config.MoneySerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@XmlRootElement(name = "employee")
@XmlAccessorType (XmlAccessType.FIELD)
public class EmployeeForXml {

	@XmlAttribute(name = "name")
	private String name = null;

	@XmlAttribute(name = "dob")
	private String dob = null;

	@XmlAttribute(name = "salary")
	@JsonSerialize(using = MoneySerializer.class)
	private Double salary = null;

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
