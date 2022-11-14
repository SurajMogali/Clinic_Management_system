package com.demo.spring.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.spring.util.Message;


@ControllerAdvice
public class UserNameExistsException extends Exception {
	@ExceptionHandler
	public ResponseEntity<Message> handleUserNotExistsException(UserNameExistsException ex) {
		return ResponseEntity.ok(new Message("User Already Exists"));

	}


}
