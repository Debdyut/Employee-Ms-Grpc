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

package com.employee.app.exception;

import reactor.core.publisher.Mono;

/**
 * 
 * {@code CustomBadRequestException} wraps exceptions related to
 * bad request scenarios.
 * 
 * @author Debdyut Hajra
 *
 */
public class CustomBadRequestException extends Exception {

	private static final long serialVersionUID = 9173243829430910191L;

	public CustomBadRequestException(String message) {
        super(message);
    }
	
	public CustomBadRequestException(Throwable cause) {
        super(cause);
    }

    public CustomBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public static Mono<CustomBadRequestException> createException(Throwable cause) {
    	CustomBadRequestException ex = new CustomBadRequestException(cause);
    	return Mono.just(ex);
    }
	
}
