package com.demo.spring.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.spring.util.Message;

@ControllerAdvice
public class DiagnosticTestExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	@ExceptionHandler
	public ResponseEntity<Message> handleAlredyExists(DiagnosticTestExistsException ex){
		return ResponseEntity.ok(new Message("Test already exists"));
	}
}
