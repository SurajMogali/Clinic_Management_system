package com.demo.spring.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DiagnosticDTOTest {
	@Test
	void testDiagnosticDTO() {
		DiagnosticDTO diagnosticDTO = new DiagnosticDTO();
		diagnosticDTO.setDiagnosticId(1);
		diagnosticDTO.setDiagnosticName("Biopsy");
		
		DiagnosticDTO diagnosticDTO2 = new DiagnosticDTO(1,"Biopsy");

		assertEquals(1, diagnosticDTO.getDiagnosticId());
		assertEquals("Biopsy", diagnosticDTO.getDiagnosticName());

	}

}
