package com.miniproj.blog.ExceptionHandlers;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Controller
public class ExceptionResponseErrorHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	final ResponseEntity<Object> handleAllExceptions(Exception ex,WebRequest request){
		
       ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
	   return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	
	@ExceptionHandler(ProfileNotFoundException.class)
	final ResponseEntity<Object> handleUserNotFoundExceptions(ProfileNotFoundException ex,WebRequest request){
		
       ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
	   return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND); 
	}
	
	@ExceptionHandler(ProfileCreationErrorHandler.class)
	final ResponseEntity<Object> handleProfileCreationExceptions(ProfileCreationErrorHandler ex,WebRequest request){
		
       ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
	   return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR); 
	}
}
