package com.demo.spring.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.demo.spring.entity.Message;

@ControllerAdvice
public class PatientNotFoundException extends Exception {

	@ExceptionHandler(PatientNotFoundException.class)
	public ResponseEntity<Message> noAppointment(PatientNotFoundException ex) {
		return ResponseEntity.ok(new Message("No Patient Found"));
	}
}