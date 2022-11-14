package com.demo.spring.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SpecialityDTOTest {

	@Test
	void testSpecialityDTO() {
		SpecialityDTO specialityDTO = new SpecialityDTO();
		specialityDTO.setSpecialityId(1);
		specialityDTO.setSpecialityName("Cardiology");
		
		SpecialityDTO specialityDTO2 = new SpecialityDTO(1,"Cardiology");

		assertEquals(1, specialityDTO.getSpecialityId());
		assertEquals("Cardiology", specialityDTO.getSpecialityName());

	}

}
