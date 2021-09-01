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

package com.employee.app.dto;

import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Employee base contains the details of the employee.
 */
@Schema(description = "Employee base contains the details of the employee.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-08-30T09:12:59.152Z[GMT]")


public class Employee   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("dob")
  private String dob = null;

  @JsonProperty("salary")
  private Double salary = null;

  @JsonProperty("age")
  private Integer age = null;

  public Employee name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name is the full name of the employee.
   * @return name
   **/
  @Schema(example = "Bruce Willis", description = "Name is the full name of the employee.")
  
    public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Employee dob(String dob) {
    this.dob = dob;
    return this;
  }

  /**
   * DOB is the date of birth of the employee.
   * @return dob
   **/
  @Schema(example = "20-08-2020", description = "DOB is the date of birth of the employee.")
  
  @Pattern(regexp="^\\d{1,2}-\\d{1,2}-\\d{4}$")   public String getDob() {
    return dob;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }

  public Employee salary(Double salary) {
    this.salary = salary;
    return this;
  }

  /**
   * Salary is the annual CTC of the employee in INR.
   * @return salary
   **/
  @Schema(example = "122111241.5", description = "Salary is the annual CTC of the employee in INR.")
  
    public Double getSalary() {
    return salary;
  }

  public void setSalary(Double salary) {
    this.salary = salary;
  }

  public Employee age(Integer age) {
    this.age = age;
    return this;
  }

  /**
   * Age is the age of the employee in years.
   * minimum: 1
   * maximum: 100
   * @return age
   **/
  @Schema(example = "20", description = "Age is the age of the employee in years.")
  
  @Min(1) @Max(100)   public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Employee employee = (Employee) o;
    return Objects.equals(this.name, employee.name) &&
        Objects.equals(this.dob, employee.dob) &&
        Objects.equals(this.salary, employee.salary) &&
        Objects.equals(this.age, employee.age);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, dob, salary, age);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Employee {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    dob: ").append(toIndentedString(dob)).append("\n");
    sb.append("    salary: ").append(toIndentedString(salary)).append("\n");
    sb.append("    age: ").append(toIndentedString(age)).append("\n");
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
