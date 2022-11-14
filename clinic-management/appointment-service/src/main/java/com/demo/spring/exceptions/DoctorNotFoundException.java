package com.demo.spring.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.spring.entity.Message;

@ControllerAdvice
public class DoctorNotFoundException extends Exception {

	
	private static final long serialVersionUID = 1L;
	
	@ExceptionHandler
	public ResponseEntity<Message> handleDoctorNotFound(DoctorNotFoundException ex){
		return ResponseEntity.ok(new Message("Doctor data is not available"));
	}

}
