package com.employee.app.exception;

import static com.employee.app.constant.GenericConstants.HTTP_BAD_REQUEST_ERROR_CODE;
import static com.employee.app.constant.GenericConstants.HTTP_BAD_REQUEST_ERROR_MSG;
import static com.employee.app.constant.GenericConstants.HTTP_INTERNAL_SERVER_ERROR_CODE;
import static com.employee.app.constant.GenericConstants.HTTP_INTERNAL_SERVER_ERROR_MSG;
import static com.employee.app.constant.GenericConstants.HTTP_UNSUPPORTED_MEDIA_TYPE_ERROR_CODE;
import static com.employee.app.constant.GenericConstants.HTTP_UNSUPPORTED_MEDIA_TYPE_ERROR_MSG;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.employee.app.dto.Error;
import com.employee.app.dto.MoreInfo;

@RestControllerAdvice
public class EmployeeExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(EmployeeExceptionHandler.class);

	private StringBuilder swaggerUrl = new StringBuilder();
	private Error errorResponse = null;

	@PostConstruct
	public void buildSwaggerUrl() {
		swaggerUrl.append("/swagger-ui.html#/");
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<Error> unSupportedMediaType(HttpMediaTypeNotSupportedException ex) {
		errorResponse = new Error();
		errorResponse.setCode(HTTP_UNSUPPORTED_MEDIA_TYPE_ERROR_CODE);;
		errorResponse.setMessage(HTTP_UNSUPPORTED_MEDIA_TYPE_ERROR_MSG);
		errorResponse.setDeveloperMessage(ex.getMessage());
		List<MoreInfo> moreInfoList = prepareMoreInfoResp();
		errorResponse.setMoreInfo(moreInfoList);

		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(errorResponse);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<Error> badRequest(MissingServletRequestParameterException ex) {
		errorResponse = new Error();
		errorResponse.setCode(HTTP_BAD_REQUEST_ERROR_CODE);
		errorResponse.setMessage(HTTP_BAD_REQUEST_ERROR_MSG);
		errorResponse.setDeveloperMessage(ex.getParameterName() + " is missing");
		List<MoreInfo> moreInfoList = prepareMoreInfoResp();
		errorResponse.setMoreInfo(moreInfoList);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		errorResponse = new Error();
		errorResponse.setCode(HTTP_BAD_REQUEST_ERROR_CODE);
		errorResponse.setMessage(HTTP_BAD_REQUEST_ERROR_MSG);
		errorResponse.setDeveloperMessage(ex.getMessage());
		List<MoreInfo> moreInfoList = new ArrayList<>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			MoreInfo moreInfo = new MoreInfo();			
			PathImpl pathImpl = (PathImpl) violation.getPropertyPath();
			moreInfo.setPropertyField(pathImpl.getLeafNode().asString());			
			moreInfo.setMessage(violation.getMessage());
			moreInfoList.add(moreInfo);
		}

		errorResponse.setMoreInfo(moreInfoList);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Error> unSupportedMediaType1(MethodArgumentNotValidException ex) {
		errorResponse = new Error();
		errorResponse.setCode(HTTP_BAD_REQUEST_ERROR_CODE);
		errorResponse.setMessage(HTTP_BAD_REQUEST_ERROR_MSG);
		errorResponse.setDeveloperMessage(ex.getMessage());
		List<MoreInfo> moreInfoList = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			FieldError fieldError = (FieldError) error;
			MoreInfo moreInfo = new MoreInfo();
			moreInfo.setPropertyField(fieldError.getField());
			moreInfo.setMessage(fieldError.getDefaultMessage());
			moreInfoList.add(moreInfo);
		}
		errorResponse.setMoreInfo(moreInfoList);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Error> handleException(Exception ex) {
		log.error("Exception {}", ex.getMessage(), ex);
		errorResponse = new Error();
		errorResponse.setCode(HTTP_INTERNAL_SERVER_ERROR_CODE);
		errorResponse.setMessage(HTTP_INTERNAL_SERVER_ERROR_MSG);
		errorResponse.setDeveloperMessage("Internal server error occurred: ");
		List<MoreInfo> moreInfoList = prepareMoreInfoResp();
		errorResponse.setMoreInfo(moreInfoList);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}

	@ExceptionHandler(CustomBadRequestException.class)
	public ResponseEntity<Error> handleException(CustomBadRequestException ex) {
		errorResponse = new Error();
		errorResponse.setCode(HTTP_BAD_REQUEST_ERROR_CODE);
		errorResponse.setMessage(HTTP_BAD_REQUEST_ERROR_MSG);
		errorResponse.setDeveloperMessage(ex.getMessage());
		List<MoreInfo> moreInfoList = new ArrayList<>();
		MoreInfo moreInfo = new MoreInfo();
		moreInfoList.add(moreInfo);
		errorResponse.setMoreInfo(moreInfoList);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}

	private List<MoreInfo> prepareMoreInfoResp() {
		List<MoreInfo> moreInfoList = new ArrayList<>();
		MoreInfo moreInfo = new MoreInfo();
		moreInfo.setMessage("Refer Swagger specification URL for more details:" + swaggerUrl);
		moreInfoList.add(moreInfo);
		return moreInfoList;
	}
}
