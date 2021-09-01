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

package com.employee.app.api;

import javax.validation.Valid;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.employee.app.dto.request.EmployeeRequest;
import com.employee.app.dto.response.EmployeeResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * {@code EmployeeAPI} defines the exposed endpoints for storing, updating and retrieving files.
 * 
 * @author Debdyut Hajra
 *
 */
public interface EmployeeAPI {

	/**
	 *  {@code Read} method retrieves the desired file.
	 * 
	 * @param filename
	 * @param download
	 * @return
	 */
	@Operation(summary = "Retrieves an uploaded file by file name.", description = "Retrieves an uploaded file by file name.", tags = {
			"Employee" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "text/csv", schema = @Schema(implementation = Resource.class))),

			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),

			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),

			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),

			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),

			@ApiResponse(responseCode = "429", description = "Too Many Requests", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),

			@ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),

			@ApiResponse(responseCode = "502", description = "Bad Gateway.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),

			@ApiResponse(responseCode = "503", description = "Service Unavailable.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),

			@ApiResponse(responseCode = "504", description = "Gateway Timeout.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))) })
	@RequestMapping(value = "/employees/{filename:.+}", produces = { "text/csv", "application/xml", "text/plain",
			"application/json" }, method = RequestMethod.GET)
	ResponseEntity<?> read(
			@Parameter(in = ParameterIn.PATH, description = "File Name is the file to be retrieved.", required = true, schema = @Schema()) @PathVariable("filename") String filename,
			@Parameter(in = ParameterIn.QUERY, description = "Download indicates whether to download the file.", schema = @Schema(defaultValue = "false")) @Valid @RequestParam(value = "download", required = false, defaultValue = "false") Boolean download);

	/**
	 * {@code Store} method stores a file in CSV and XML formats.
	 * 
	 * @param fileType
	 * @param body
	 * @param fileName
	 * @return
	 */
	@Operation(summary = "Stores employee information in CSV/XML format.", description = "Stores employee information in CSV/XML format.", tags = {
			"Employee" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeResponse.class))),

			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),

			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),

			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),

			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),

			@ApiResponse(responseCode = "429", description = "Too Many Requests", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),

			@ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),

			@ApiResponse(responseCode = "502", description = "Bad Gateway.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),

			@ApiResponse(responseCode = "503", description = "Service Unavailable.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),

			@ApiResponse(responseCode = "504", description = "Gateway Timeout.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))) })
	@RequestMapping(value = "/employees", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.POST)
	ResponseEntity<?> store(
			@Parameter(in = ParameterIn.HEADER, description = "File Type is the format in which file should be saved.", required = true, schema = @Schema(allowableValues = {
					"CSV", "XML" })) @RequestHeader(value = "fileType", required = true) String fileType,
			@Parameter(in = ParameterIn.DEFAULT, description = "The list of employee information to be stored in desired format.", required = true, schema = @Schema()) @Valid @RequestBody EmployeeRequest body,
			@Parameter(in = ParameterIn.HEADER, description = "File Name is the preferred name of the file to be stored. If file name is not provided, then a UUID is set as the default file name.", schema = @Schema()) @RequestHeader(value = "fileName", required = false) String fileName);

	/**
	 * {@code Update} method updates an existing file.
	 * 
	 * @param fileType
	 * @param fileName
	 * @param body
	 * @return
	 */
	@Operation(summary = "Update employee information in specified file.", description = "Update employee information in specified file.", tags = {
			"Employee" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EmployeeResponse.class))),

			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),

			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),

			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),

			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),

			@ApiResponse(responseCode = "429", description = "Too Many Requests", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),

			@ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),

			@ApiResponse(responseCode = "502", description = "Bad Gateway.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),

			@ApiResponse(responseCode = "503", description = "Service Unavailable.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))),

			@ApiResponse(responseCode = "504", description = "Gateway Timeout.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))) })
	@RequestMapping(value = "/employees", produces = { "application/json" }, consumes = {
			"application/json" }, method = RequestMethod.PUT)
	ResponseEntity<?> update(
			@Parameter(in = ParameterIn.HEADER, description = "File Type is the format in which file should be saved.", required = true, schema = @Schema(allowableValues = {
					"CSV", "XML" })) @RequestHeader(value = "fileType", required = true) String fileType,
			@Parameter(in = ParameterIn.HEADER, description = "File Name is the preferred name of the file to be stored.", required = true, schema = @Schema()) @RequestHeader(value = "fileName", required = true) String fileName,
			@Parameter(in = ParameterIn.DEFAULT, description = "The list of employee information to be stored in desired format.", required = true, schema = @Schema()) @Valid @RequestBody EmployeeRequest body);

}
