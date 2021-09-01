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

package com.employee.app.helper;

import static com.employee.app.constant.GenericConstants.CSV_EXTENSION;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.employee.app.config.StorageProperties;
import com.employee.app.exception.StorageException;
import com.employee.app.model.EmployeeForCsv;
import com.employee.app.model.EmployeeModel;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

/**
 * 
 * {@code EmployeeCsvHelper} provides helper methods to
 * store and update CSV files in the storage location.
 * 
 * @author Debdyut Hajra
 *
 */
@Component
public class EmployeeCsvHelper {
	
	/**
	 * {@code RootLocation} is the storage location where the files will be stored.
	 */
	private final Path rootLocation;
	
	/**
	 * Autowire the dependencies through constructor injection.
	 * 
	 * @param properties
	 */
    public EmployeeCsvHelper(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }
	
    /**
     * Check if root location can be accessed.
     */
    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage location", e);
        }
    }
	
    /**
     * 
     * {@code store} method helps to store employee records to CSV file
     * in the storage location.
     * 
     * @param fileName
     * @param employees
     * @return {@code java.io.File}
     */
    public File store (String fileName, EmployeeModel[] employees) {
    	// Check if file name is null or empty
    	if (StringUtils.isBlank(fileName)) {
    		throw new StorageException("Failed to store file with empty file name");
    	}
    	
    	// Check if employee records is empty
    	if (ArrayUtils.isEmpty(employees)) {
    		throw new StorageException("Failed to store empty file " + fileName);
    	}
    	
    	// Check for illegal file names
    	if (fileName.contains("..")) {
            // This is a security check
            throw new StorageException(
                    "Cannot store file with relative path outside current directory "
                            + fileName);
        }
    	
    	// Create a new file object at the storage location
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
    	
    	// Write the employee records to the desired file location
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
    	
    	// Return the saved file
    	return file;
	}
	
}
