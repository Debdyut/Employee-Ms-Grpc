package com.employee.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.employee.app.model.EmployeeForCsv;
import com.employee.app.model.EmployeeModel;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@Configuration
public class EmployeeConfig {

	@Bean
	public CsvMapper employeeCsvMapper() {
		CsvMapper csvMapper = new CsvMapper();
		CsvSchema csvSchema = csvMapper
								.schemaFor(EmployeeForCsv.class)
								.withHeader();

		csvMapper.addMixIn(EmployeeModel.class, EmployeeForCsv.class).writerFor(EmployeeModel[].class).with(csvSchema);
		return csvMapper;
	}
}
