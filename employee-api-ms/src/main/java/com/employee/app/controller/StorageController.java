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

package com.employee.app.controller;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.employee.app.service.IStorageService;

/**
 * 
 * {@code StorageController} defines endpoints to list all available files
 * and retrieve the file contents by file name.
 * 
 * @author Debdyut Hajra
 *
 */
@RestController
public class StorageController {
	
	/**
	 * {@code StorageService} provides the necessary functionalities to
	 * list all available files and retrieve the file contents by file name.
	 * 
	 */
	private final IStorageService storageService;
	
	/**
	 * {@code StringEncryptor} helps to encrypt or decrypt the strings.
	 */
	private final StringEncryptor jasyptStringEncryptor;

	/**
	 * Autowire the dependencies through constructor injection.
	 * 
	 * @param storageService
	 * @param jasyptStringEncryptor
	 */
    public StorageController(
    		final IStorageService storageService, 
    		final StringEncryptor jasyptStringEncryptor) 
    {
        this.storageService = storageService;
        this.jasyptStringEncryptor = jasyptStringEncryptor;
    }
    
    /**
     * 
     * {@code ListAllFiles} returns all the available files
     * in the storage location.
     * 
     * @return {@code java.util.List<String>}
     */
    @GetMapping("/")
    public ResponseEntity<?> listAllFiles() {
    	
    	// Retrieve all available paths and convert to list of strings
        List<String> files = storageService.loadAll().map(Path::toString)
                .collect(Collectors.toList());

        // Return success response with the list of available paths
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }
    
    /**
     * 
     * {@code DownloadFile} returns the file contents of the requested file
     * as encrypted string.
     * 
     * @return {@code java.lang.String}
     */
    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<?> downloadFile(@PathVariable String filename) {

    	// Retrieve file content from the requested file
        String fileContent = storageService.readFile(filename);
        
        // Encrypt the file content before sending the response
        fileContent = jasyptStringEncryptor.encrypt(fileContent);
        
        // Return success response with the encrypted file content
        return ResponseEntity.ok().body(fileContent);
    }

}
