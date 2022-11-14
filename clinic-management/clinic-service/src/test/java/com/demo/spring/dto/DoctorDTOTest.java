package com.demo.spring.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DoctorDTOTest {

	@Test
	void testDoctorDTO() {
		DoctorDTO doctorDTO = new DoctorDTO();
		
		DoctorDTO doctorDTO2 = new DoctorDTO(101,"suraj","mogali","surajmogali@gmail.com");
		
		doctorDTO.setFirstName("suraj");
		doctorDTO.setLastName("mogali");
		doctorDTO.setEmail("surajmogali@gmail.com");
		doctorDTO.setDoctorId(101);

		assertEquals("suraj", doctorDTO.getFirstName());
		assertEquals("mogali", doctorDTO.getLastName());
		assertEquals("surajmogali@gmail.com", doctorDTO.getEmail());
		assertEquals(101, doctorDTO.getDoctorId());

	}
}
