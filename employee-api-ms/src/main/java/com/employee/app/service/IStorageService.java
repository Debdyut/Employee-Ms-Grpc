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

package com.employee.app.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * {@code IStorageService} defines the core functionalities of the storage service.
 * 
 * @author Debdyut Hajra
 *
 */
public interface IStorageService {

	/**
	 * {@code Init} method runs required checks and instructions
	 * after construction of the object.
	 */
    void init();

    /**
     * {@code Store} method stores a given multi-part file to the storage location.
     * 
     * @param file 
     * @return {@code java.lang.String} File name after saving.
     */
	String store(MultipartFile file);

	/**
	 * {@code LoadAll} method returns the available file paths in the storage location.
	 * 
	 * @return {@code java.util.stream.Stream<Path>}
	 */
    Stream<Path> loadAll();

    /**
     * {@code Load} method returns the path of the desired file.
     * 
     * @param filename
     * @return {@code java.nio.file.Path}
     */
    Path load(String filename);

    /**
	 * {@code LoadAsResource} method returns the desired file as resource.
	 * 
	 * @return {@code org.springframework.core.io.Resource}
	 */
    Resource loadAsResource(String filename);

    /**
     * {@code DeleteAll} method deletes all files from the storage location.
     */
    void deleteAll();

    /**
	 * {@code ReadFile} method returns the file contents of the desired file.
	 * 
	 * @return {@code java.lang.String}
	 */
	String readFile(String filename);

}
