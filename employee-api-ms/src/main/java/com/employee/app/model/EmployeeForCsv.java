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

@JsonPropertyOrder({
    "name",
    "dob",
    "salary",
    "age"
})
public class EmployeeForCsv {
    
    @JsonProperty("name")
    private String name = null;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("dob")
	private LocalDate dob = null;

    @JsonProperty("salary")
    @JsonSerialize(using = MoneySerializer.class)
	private Double salary = null;

    @JsonProperty("age")
	private Integer age = null;
	
}
