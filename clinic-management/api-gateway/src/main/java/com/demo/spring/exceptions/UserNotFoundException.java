package com.demo.spring.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.spring.util.Message;
@ControllerAdvice
public class UserNotFoundException extends Exception {
	@ExceptionHandler
	public ResponseEntity<Message> handleUserNotFoundException(UserNotFoundException ex) {
		return ResponseEntity.ok(new Message("User Not Found"));

	}

}
