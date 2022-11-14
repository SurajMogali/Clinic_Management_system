package com.demo.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "diagnostic")
public class Diagnostic {

	@Id
	@Column(name = "diagnostic_id")
	private Integer diagnosticId;
	@Column(name = "diagnostic_name")
	private String diagnosticName;

	public Diagnostic() {
		super();
	}

	public Diagnostic(Integer diagnosticId, String diagnosticName) {
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
