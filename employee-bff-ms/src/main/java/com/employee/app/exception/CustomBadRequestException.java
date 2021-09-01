package com.employee.app.exception;

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

    public CustomBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
	
}
