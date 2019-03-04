package com.sango.microservices.students.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CommonExceptionController extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<Object> handleExceptionDefault(
			Exception ex, WebRequest request) {
		CommonExceptionResponse commonExceptionResponse = new CommonExceptionResponse(new Date(), 
																					ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(commonExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
			CommonExceptionResponse commonExceptionRespose = new CommonExceptionResponse(new Date(),ex.getMessage(),
																ex.getBindingResult().toString());
		return new ResponseEntity<Object>(commonExceptionRespose,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value=NoStudentFoundException.class)
	public ResponseEntity<Object> handleExceptionNoStudentFound(
			Exception ex, WebRequest request) {
		CommonExceptionResponse commonExceptionResponse = new CommonExceptionResponse(new Date(), 
																					ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(commonExceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=StudentNotFoundException.class)
	public ResponseEntity<Object> handleExceptionStudentNotFound(
			Exception ex, WebRequest request) {
		CommonExceptionResponse commonExceptionResponse = new CommonExceptionResponse(new Date(), 
																					ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(commonExceptionResponse, HttpStatus.NOT_FOUND);
	}

}
