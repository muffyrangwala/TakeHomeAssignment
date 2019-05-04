package com.walmart.search.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.walmart.search.vo.ErrorDetails;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
	  ErrorDetails errorDetails = new ErrorDetails(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.toString() , ex.getMessage());
	  return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorDetails> handleMethodArgumentTypeMismatchExceptions(RuntimeException ex, WebRequest request) {
	  ErrorDetails errorDetails = new ErrorDetails(new Date(), HttpStatus.BAD_REQUEST.toString() , ex.getMessage());
	  return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorDetails> IllegalArgumentException(RuntimeException ex, WebRequest request) {
		 ErrorDetails errorDetails = new ErrorDetails(new Date(), HttpStatus.BAD_REQUEST.toString() , ex.getMessage());
		 return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}
