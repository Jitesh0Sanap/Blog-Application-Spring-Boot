package com.blog.blog_project.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.blog_project.payloads.ApiResponce;

@RestControllerAdvice
public class GloableExceptionHandeling {
	
	
	@ExceptionHandler(ResourceNotfoundException.class)
	public ResponseEntity<ApiResponce> resourceNotFoundExcwptionHandeler(ResourceNotfoundException ex){
		
		String message  = ex.getMessage();
		ApiResponce apiResponce = new ApiResponce(message, false);
		return new ResponseEntity<ApiResponce>(apiResponce, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handelMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String, String> resp = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError)error).getField();
			String defaultMessage = error.getDefaultMessage();
			resp.put(fieldName, defaultMessage);
		});
		
		return new ResponseEntity<Map<String,String>>(resp , HttpStatus.BAD_REQUEST);
	}

}
