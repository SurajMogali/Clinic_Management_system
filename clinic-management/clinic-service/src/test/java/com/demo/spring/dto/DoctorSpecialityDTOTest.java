package com.demo.spring.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DoctorSpecialityDTOTest {
	
	@Test
	void testDiagnosticDTO() {
		DoctorSpecialityDTO doctorSpecialityDTO = new DoctorSpecialityDTO();
		doctorSpecialityDTO.setDoctorId(101);
		doctorSpecialityDTO.setSpecialityId(2);
		
		DoctorSpecialityDTO doctorSpecialityDTO2= new DoctorSpecialityDTO(101,2);
		
		

		assertEquals(101, doctorSpecialityDTO.getDoctorId());
		assertEquals(2, doctorSpecialityDTO.getSpecialityId());

	}
	
	

	
	
}
