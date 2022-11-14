package com.demo.spring.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.demo.spring.dto.PatientDiagnosticDTO;

public class PatientDiagnosticDTOTest {

	@Test
	void testPtientDiagnostic() {
		PatientDiagnostic patientDiagnostic = new PatientDiagnostic();
		patientDiagnostic.setDiagnosticID(3);
		patientDiagnostic.setPatientID(201);
		
		PatientDiagnostic patientDiagnosticDTO2 = new PatientDiagnostic(3,201);

		assertEquals(3, patientDiagnostic.getDiagnosticID());
		assertEquals(201, patientDiagnostic.getPatientID());

	}

}
