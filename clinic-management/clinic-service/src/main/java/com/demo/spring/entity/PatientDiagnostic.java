package com.demo.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "patient_diagnostic")
public class PatientDiagnostic {

	@Id
	@SequenceGenerator(sequenceName = "PatientDiagnostic_sequence", initialValue = 5, allocationSize = 1, name = "PatientDiagnostic_Id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PatientDiagnostic_Id")

	@Column(name = "id")
	private Integer id;

	@Column(name = "diagnostic_id")
	private Integer DiagnosticID;

	@Column(name = "patient_id")
	private Integer PatientID;

	public PatientDiagnostic() {
	}

	public PatientDiagnostic(Integer diagnosticID, Integer patientID) {
		super();
		PatientID = patientID;
		DiagnosticID = diagnosticID;
	}

	public PatientDiagnostic(Integer id, Integer patientID, Integer diagnosticID) {
		super();
		this.id = id;
		PatientID = patientID;
		DiagnosticID = diagnosticID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

}