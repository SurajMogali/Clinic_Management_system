package com.demo.spring.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PatientTest {

	@Test
	void testPatient() {

		Patient patient2 = new Patient(100, "john", "walker", "johnwalker@gmail.com");

		Patient patient = new Patient();
		patient.setFirstName("john");
		patient.setLastName("walker");
		patient.setEmail("johnwalker@gmail.com");
		patient.setPatientId(100);

		assertEquals("john", patient.getFirstName());
		assertEquals("walker", patient.getLastName());
		assertEquals("johnwalker@gmail.com", patient.getEmail());
		assertEquals(100, patient.getPatientId());

	}

}
