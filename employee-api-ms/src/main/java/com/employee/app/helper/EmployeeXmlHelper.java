package com.employee.app.helper;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.employee.app.config.StorageProperties;
import com.employee.app.exception.StorageException;
import com.employee.app.mapper.EmployeeMapper;
import com.employee.app.model.EmployeeListForXml;
import com.employee.app.model.EmployeeModel;

@Component
public class EmployeeXmlHelper {

	private static final String XML_EXTENSION = ".xml";

	private final Path rootLocation;

	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	public EmployeeXmlHelper(StorageProperties properties) {
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

	public File store(String fileName, EmployeeModel[] employees) {

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
    		if (!fileName.endsWith(XML_EXTENSION))
    			fileName += XML_EXTENSION;
    		file = this.rootLocation.resolve(fileName).toFile();
    	} catch (UnsupportedOperationException e) {
    		throw new StorageException(
                    "Cannot store file with relative path: "
                            + fileName);
    	}
    	
    	var employeeList = employeeMapper.modelToJaxbModel(employees);
		
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

		return file;
	}

}
