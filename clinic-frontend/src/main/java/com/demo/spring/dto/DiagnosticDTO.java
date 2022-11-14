package com.demo.spring.dto;

public class DiagnosticDTO {

	
	private Integer diagnosticId;
	private String diagnosticName;

	public DiagnosticDTO() {
		super();
	}

	public DiagnosticDTO(Integer diagnosticId, String diagnosticName) {
		super();
		this.diagnosticId = diagnosticId;
		this.diagnosticName = diagnosticName;
	}

	public Integer getDiagnosticId() {
		return diagnosticId;
	}

	public void setDiagnosticId(Integer diagnosticId) {
		this.diagnosticId = diagnosticId;
	}

	public String getDiagnosticName() {
		return diagnosticName;
	}

	public void setDiagnosticName(String diagnosticName) {
		this.diagnosticName = diagnosticName;
	}

}
