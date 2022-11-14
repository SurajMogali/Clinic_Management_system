package com.demo.spring.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.spring.entity.Message;

@ControllerAdvice
public class PatientNotExistsException extends Exception {

	@ExceptionHandler(PatientNotExistsException.class)
	public ResponseEntity<Message> noAppointment(PatientNotExistsException ex) {
		return ResponseEntity.ok(new Message("No Patient Found"));
	}
}