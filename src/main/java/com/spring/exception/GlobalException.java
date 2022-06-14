package com.spring.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


	@RestControllerAdvice
	public class GlobalException extends RuntimeException {

		@ExceptionHandler(ResourceNotFoundException.class)
		ResponseEntity<ApiResponce> ResourceNotFoundException(ResourceNotFoundException ex) {
			String message = ex.getMessage();
			ApiResponce response = new ApiResponce(message, false);
			return new ResponseEntity<ApiResponce>(response, HttpStatus.NOT_FOUND);
		}

		@ExceptionHandler(MethodArgumentNotValidException.class)
		ResponseEntity<Map<String, String>> handleMethodArgNotFoundException(MethodArgumentNotValidException ex) {
			Map<String, String> map = new HashMap();
			ex.getBindingResult().getAllErrors().forEach(error -> {

				String fieldName =((FieldError)error).getField();
				String message =error.getDefaultMessage();
				map.put(fieldName, message);
			});
			return new ResponseEntity<Map<String,String>>(map,HttpStatus.BAD_REQUEST);
		}
}
