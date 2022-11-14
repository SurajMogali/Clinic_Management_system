package com.demo.spring.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PatientDiagnosticDTOTest {

	@Test
	void testPatientDiagnosticDTO() {
		PatientDiagnosticDTO patientDiagnosticDTO = new PatientDiagnosticDTO();
		patientDiagnosticDTO.setDiagnosticID(3);
		patientDiagnosticDTO.setPatientID(201);
		
		PatientDiagnosticDTO patientDiagnosticDTO2 = new PatientDiagnosticDTO(3,201);

		assertEquals(3, patientDiagnosticDTO.getDiagnosticID());
		assertEquals(201, patientDiagnosticDTO.getPatientID());

	}

}
