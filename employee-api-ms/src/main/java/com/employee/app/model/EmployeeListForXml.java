package com.employee.app.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employees")
@XmlAccessorType (XmlAccessType.FIELD)
public class EmployeeListForXml {

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
