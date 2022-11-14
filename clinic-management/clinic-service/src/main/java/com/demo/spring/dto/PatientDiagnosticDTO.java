package com.demo.spring.dto;

public class PatientDiagnosticDTO {

	private Integer PatientID;

	private Integer DiagnosticID;
	
	private Integer id;

	public PatientDiagnosticDTO() {
	}

	public PatientDiagnosticDTO(Integer patientID, Integer diagnosticID) {
		super();
		PatientID = patientID;
		DiagnosticID = diagnosticID;
	}
	
	
	
	

	public PatientDiagnosticDTO(Integer patientID, Integer diagnosticID, Integer id) {
		super();
		PatientID = patientID;
		DiagnosticID = diagnosticID;
		this.id = id;
	}

	public Integer getPatientID() {
		return PatientID;
	}

	public void setPatientID(Integer patientID) {
		PatientID = patientID;
	}

	public Integer getDiagnosticID() {
		return DiagnosticID;
	}

	public void setDiagnosticID(Integer diagnosticID) {
		DiagnosticID = diagnosticID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	

}