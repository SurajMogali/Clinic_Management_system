package com.demo.spring.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.demo.spring.dto.CredentialsDTO;

public class CredentialsTest {
	
	@Test
	void testCredentials() {
		Credentials credentials=new Credentials();
		credentials.setUserName("receptionist");
		credentials.setPassword("receptionist");
		
		Credentials credential=new Credentials("admin","admin");
		
		CredentialsDTO credentialDTO=new CredentialsDTO("admin","admin");
		
		assertEquals("receptionist",credentials.getUserName());
		assertEquals("receptionist",credentials.getPassword());
		
		
		
		
	}

}
