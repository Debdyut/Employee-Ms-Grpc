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

import static com.employee.app.constant.GenericConstants.XML_EXTENSION;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.employee.app.config.StorageProperties;
import com.employee.app.exception.StorageException;
import com.employee.app.mapper.EmployeeMapper;
import com.employee.app.model.EmployeeListForXml;
import com.employee.app.model.EmployeeModel;

/**
 * 
 * {@code EmployeeXmlHelper} provides helper methods to
 * store and update XML files in the storage location.
 * 
 * @author Debdyut Hajra
 *
 */
@Component
public class EmployeeXmlHelper {

	/**
	 * {@code RootLocation} is the storage location where the files will be stored.
	 */
	private final Path rootLocation;

	/**
	 * {@code EmployeeMapper} helps to transform employee objects to desired form.
	 */
	private final EmployeeMapper employeeMapper;
	
	/**
	 * Autowire the dependencies through constructor injection.
	 * 
	 * @param properties
	 * @param employeeMapper
	 */
	public EmployeeXmlHelper(
			final StorageProperties properties,
			final EmployeeMapper employeeMapper) 
	{
		this.rootLocation = Paths.get(properties.getLocation());
		this.employeeMapper = employeeMapper;
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
     * {@code store} method helps to store employee records to XML file
     * in the storage location.
     * 
     * @param fileName
     * @param employees
     * @return {@code java.io.File}
     */
	public File store(String fileName, EmployeeModel[] employees) {
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
    		if (!fileName.endsWith(XML_EXTENSION))
    			fileName += XML_EXTENSION;
    		file = this.rootLocation.resolve(fileName).toFile();
    	} catch (UnsupportedOperationException e) {
    		throw new StorageException(
                    "Cannot store file with relative path: "
                            + fileName);
    	}
    	
    	// Throw error if file already exists
		if (file.exists()) {
			throw new StorageException("File already exists: " + fileName);				
		}
    	
    	// Convert employee model to jaxb models for saving to XML file.
    	var employeeList = employeeMapper.modelToJaxbModel(employees);
    	
    	// Write the employee records to the desired file location
		try {
			// Create JAXB Context
			JAXBContext jaxbContext = JAXBContext.newInstance(EmployeeListForXml.class);

			// Create Marshaller
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// Required formatting
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Writes XML file to file-system
			jaxbMarshaller.marshal(employeeList, file);
			
		} catch (JAXBException e) {
			throw new StorageException(
                    "Unable to save XML to "
                            + fileName, e);
		}

		// Return the saved file
		return file;
	}
	
	/**
     * 
     * {@code Update} method helps to update employee records in the XML file
     * in storage location.
     * 
     * @param fileName
     * @param employees
     * @return {@code java.io.File}
     */
	public File update(String fileName, EmployeeModel[] employees) {
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
    		if (!fileName.endsWith(XML_EXTENSION))
    			fileName += XML_EXTENSION;
    		file = this.rootLocation.resolve(fileName).toFile();
    	} catch (UnsupportedOperationException e) {
    		throw new StorageException(
                    "Cannot store file with relative path: "
                            + fileName);
    	}
    	
    	// Convert employee model to jaxb models for saving to XML file.
    	var employeeList = employeeMapper.modelToJaxbModel(employees);
    	
    	// Write the employee records to the desired file location
		try {
			// Create JAXB Context
			JAXBContext jaxbContext = JAXBContext.newInstance(EmployeeListForXml.class);

			// Create Marshaller
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// Required formatting
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Writes XML file to file-system
			jaxbMarshaller.marshal(employeeList, file);
			
		} catch (JAXBException e) {
			throw new StorageException(
                    "Unable to save XML to "
                            + fileName, e);
		}

		// Return the saved file
		return file;
	}

}
