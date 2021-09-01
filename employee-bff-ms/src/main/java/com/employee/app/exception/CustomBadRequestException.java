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
