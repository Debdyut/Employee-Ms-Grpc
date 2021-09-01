package com.employee.app.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.employee.app.config.StorageProperties;
import com.employee.app.exception.StorageException;
import com.employee.app.model.EmployeeForCsv;
import com.employee.app.model.EmployeeModel;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@Component
public class EmployeeCsvHelper {

	private static final String CSV_EXTENSION = ".csv";
	
	private final Path rootLocation;
	
	@Autowired
    public EmployeeCsvHelper(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }
	
    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage location", e);
        }
    }
	
    public File store (String fileName, EmployeeModel[] employees) {
    	if (StringUtils.isBlank(fileName)) {
    		throw new StorageException("Failed to store file with empty file name");
    	}
    	
    	if (ArrayUtils.isEmpty(employees)) {
    		throw new StorageException("Failed to store empty file " + fileName);
    	}
    	
    	if (fileName.contains("..")) {
            // This is a security check
            throw new StorageException(
                    "Cannot store file with relative path outside current directory "
                            + fileName);
        }
    	
    	File file = null;
    	try {
    		if (!fileName.endsWith(CSV_EXTENSION))
    			fileName += CSV_EXTENSION;
    		file = this.rootLocation.resolve(fileName).toFile();
    	} catch (UnsupportedOperationException e) {
    		throw new StorageException(
                    "Cannot store file with relative path: "
                            + fileName);
    	}
    	
    	try {
    		CsvMapper csvMapper = new CsvMapper();
    		CsvSchema csvSchema = csvMapper
    								.schemaFor(EmployeeForCsv.class)
    								.withHeader();

    		csvMapper.addMixIn(EmployeeModel.class, EmployeeForCsv.class);
    		csvMapper.writerFor(EmployeeModel[].class)
    			.with(csvSchema)
    			.writeValue(file, employees);
    	} catch (JsonMappingException | JsonGenerationException e) {
    		throw new StorageException(
                    "Unable to parse data to csv for "
                            + fileName, e);
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + fileName, e);
		}
    	return file;
	}
	
}
