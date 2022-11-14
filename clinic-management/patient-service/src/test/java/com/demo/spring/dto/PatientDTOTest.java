package com.demo.spring.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.demo.spring.dto.PatientDTO;

public class PatientDTOTest {
	
	@Test
	void testPatientDTO() {
		
		PatientDTO patient=new PatientDTO(100,"john","walker","johnwalker@gmail.com");
		
		PatientDTO patientDTO=new PatientDTO();
		patientDTO.setFirstName("john");
		patientDTO.setLastName("walker");
		patientDTO.setEmail("johnwalker@gmail.com");
		patientDTO.setPatientId(100);
		
		assertEquals("john",patientDTO.getFirstName());
		assertEquals("walker",patientDTO.getLastName());
		assertEquals("johnwalker@gmail.com",patientDTO.getEmail());
		assertEquals(100,patientDTO.getPatientId());
		
		
		
		
		
		
		
	}

}
