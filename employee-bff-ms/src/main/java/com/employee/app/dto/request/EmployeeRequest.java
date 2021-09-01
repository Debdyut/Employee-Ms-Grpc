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

package com.employee.app.dto.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.employee.app.dto.Employee;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Employee Request contains the list of employee information to be added.
 */
@Schema(description = "Employee Request contains the list of employee information to be added.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-08-30T09:12:59.152Z[GMT]")


public class EmployeeRequest   {
  @JsonProperty("employees")
  @Valid
  private List<Employee> employees = null;

  public EmployeeRequest employees(List<Employee> employees) {
    this.employees = employees;
    return this;
  }

  public EmployeeRequest addEmployeesItem(Employee employeesItem) {
    if (this.employees == null) {
      this.employees = new ArrayList<Employee>();
    }
    this.employees.add(employeesItem);
    return this;
  }

  /**
   * Employee contains the details of an employee.
   * @return employees
   **/
  @Schema(description = "Employee contains the details of an employee.")
      @Valid
    public List<Employee> getEmployees() {
    return employees;
  }

  public void setEmployees(List<Employee> employees) {
    this.employees = employees;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EmployeeRequest employeeRequest = (EmployeeRequest) o;
    return Objects.equals(this.employees, employeeRequest.employees);
  }

  @Override
  public int hashCode() {
    return Objects.hash(employees);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EmployeeRequest {\n");
    
    sb.append("    employees: ").append(toIndentedString(employees)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
