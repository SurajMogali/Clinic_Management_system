package com.demo.spring.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.demo.spring.entity.Credentials;

public class CredentialsDTOTest {
	
	@Test
	void testCredentialsDTO() {
		CredentialsDTO credentialsDTO=new CredentialsDTO();
		credentialsDTO.setUserName("receptionist");
		credentialsDTO.setPassword("receptionist");
		
		CredentialsDTO credentialDTO=new CredentialsDTO("admin","admin");
		
		Credentials credentials=new Credentials("admin","admin");
		
		assertEquals("receptionist",credentialsDTO.getUserName());
		assertEquals("receptionist",credentialsDTO.getPassword());
		
		
		
		
	}

}
