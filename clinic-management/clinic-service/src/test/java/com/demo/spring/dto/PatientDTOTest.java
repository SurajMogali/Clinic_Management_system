package com.demo.spring.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PatientDTOTest {
	
	@Test
	void testPatientDTO() {
		PatientDTO patientDTO=new PatientDTO();
		patientDTO.setFirstName("john");
		patientDTO.setLastName("walker");
		patientDTO.setEmail("johnwalker@gmail.com");
		patientDTO.setPatientId(100);
		
		PatientDTO patientDTO2=new PatientDTO(100,"john","walker","johnwalker@gmail.com");
		
		assertEquals("john",patientDTO.getFirstName());
		assertEquals("walker",patientDTO.getLastName());
		assertEquals("johnwalker@gmail.com",patientDTO.getEmail());
		assertEquals(100,patientDTO.getPatientId());
		
		
		
		
		
		
		
	}

}
