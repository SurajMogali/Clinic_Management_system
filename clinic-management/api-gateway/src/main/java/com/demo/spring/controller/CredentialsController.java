package com.demo.spring.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.dto.CredentialsDTO;
import com.demo.spring.entity.Credentials;
import com.demo.spring.exceptions.UserNotFoundException;
import com.demo.spring.services.CredentialsService;
import com.demo.spring.util.Message;

import io.swagger.v3.oas.annotations.OpenAPI30;

@RestController
@OpenAPI30
@RequestMapping("/login")

public class CredentialsController {
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	@Autowired
	CredentialsService credentialsService;

	/**
	 * this method will return the user
	 * 
	 * @param credentialsDTO
	 * @return
	 * @throws UserNotFoundException
	 */
	@PostMapping(path = "/find", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> findUserName(@RequestBody CredentialsDTO credentialsDTO)
			throws UserNotFoundException {
		Credentials credential = new Credentials(credentialsDTO.getUserName(), credentialsDTO.getPassword());
		logger.info("The method had called the service to find the user");
		return ResponseEntity.ok(credentialsService.findUserNameService(credential));

	}

	/**
	 * this method will update the user
	 * 
	 * @param credentialsDTO
	 * @return
	 * @throws UserNotFoundException
	 */
	@PatchMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> updatePassword(@RequestBody CredentialsDTO credentialsDTO)
			throws UserNotFoundException {
		Credentials credential = new Credentials(credentialsDTO.getUserName(), credentialsDTO.getPassword());
		logger.info("The method had called the service to update the user");
		return ResponseEntity.ok(credentialsService.updateUser(credential));
	}

}