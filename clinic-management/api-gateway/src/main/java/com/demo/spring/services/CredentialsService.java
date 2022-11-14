package com.demo.spring.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring.entity.Credentials;
import com.demo.spring.exceptions.UserNotFoundException;
import com.demo.spring.repository.CredentialRepository;
import com.demo.spring.util.Message;

@Service

public class CredentialsService {
	
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	

	@Autowired
	CredentialRepository credentialRepository;

	public Message findUserNameService(Credentials credentials) throws UserNotFoundException {
		List<Credentials> listCred = credentialRepository.findUser(credentials.getUserName(),
				credentials.getPassword());
		logger.info("Credential Service: Find the user ");
	
		if (listCred.isEmpty()) {
			logger.error("Credential Service: Exception :User Not Found Exception");
			throw new UserNotFoundException();
		} else {
			return new Message("User Found");
		}
	}

	public Message updateUser(Credentials credentials) throws UserNotFoundException {
		Optional<Credentials> credOp = credentialRepository.findById(credentials.getUserName());
		logger.info("Credential Service: Update the user");
		if (credOp.isEmpty()) {
			logger.error("Diagnostic Service: Exception :User Not Found Exception");
			throw new UserNotFoundException();
		} else {
			credentialRepository.updateUser(credentials.getUserName(), credentials.getPassword());
			return new Message("User Updated");
		}
	}

	
}