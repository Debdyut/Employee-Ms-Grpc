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

import java.time.LocalDate;

import com.employee.app.config.MoneySerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

/**
 * 
 * {@code EmployeeForCsv} is the employee schema for saving as CSV.
 * 
 * @author Debdyut Hajra
 *
 */
@JsonPropertyOrder({
    "name",
    "dob",
    "salary",
    "age"
})
public class EmployeeForCsv {
    
	/**
	 * {@code Name} of the employee.
	 */
    @JsonProperty("name")
    private String name = null;

    /**
	 * {@code DOB} is the date of birth of the employee.
	 */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("dob")
	private LocalDate dob = null;

    /**
	 * {@code Salary} of the employee in INR.
	 */
    @JsonProperty("salary")
    @JsonSerialize(using = MoneySerializer.class)
	private Double salary = null;

    /**
	 * {@code Age} of the employee in years.
	 */
    @JsonProperty("age")
	private Integer age = null;
	
}
