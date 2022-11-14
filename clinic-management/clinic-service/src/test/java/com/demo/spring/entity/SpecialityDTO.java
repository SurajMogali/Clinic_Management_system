package com.demo.spring.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SpecialityDTO {

	@Test
	void testSpeciality() {
		Speciality speciality = new Speciality();
		speciality.setSpecialityId(1);
		speciality.setSpecialityName("Cardiology");

		assertEquals(1, speciality.getSpecialityId());
		assertEquals("Cardiology", speciality.getSpecialityName());

	}

}
