package com.demo.spring.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.demo.spring.dto.DoctorSpecialityDTO;

public class DoctorSpecialityTest {
	
	@Test
	void testDoctorSpeciality() {
		DoctorSpeciality doctorSpeciality = new DoctorSpeciality();
		doctorSpeciality.setDoctorId(101);
		doctorSpeciality.setSpecialityId(2);
		
		DoctorSpeciality doctorSpecialityDTO2= new DoctorSpeciality(101,2);

		assertEquals(101, doctorSpeciality.getDoctorId());
		assertEquals(2, doctorSpeciality.getSpecialityId());

	}
	
	

	
	
}
