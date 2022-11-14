package com.demo.spring.services;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.spring.entity.Diagnostic;
import com.demo.spring.entity.PatientDiagnostic;
import com.demo.spring.exceptions.DiagnosticNotFoundException;
import com.demo.spring.repository.DiagnosticRepository;
import com.demo.spring.repository.PatientDiagnosticRepository;
import com.demo.spring.util.Message;
import com.demo.spring.util.ServerConfiguration;

@Service
public class PatientDiagnosticService {
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ServerConfiguration server;

	@Autowired
	DiagnosticRepository diagnosticRepository;

	@Autowired
	PatientDiagnosticRepository patientDiagnosticRepository;

	public Message addTestToPatient(int patientId, int testId) throws DiagnosticNotFoundException {
		Optional<Diagnostic> diagnosticOp = diagnosticRepository.findById(testId);
		if (diagnosticOp.isPresent()) {
			PatientDiagnostic patientDiagnostic = new PatientDiagnostic(testId, patientId);
			patientDiagnosticRepository.save(patientDiagnostic);
			logger.info("The method had called the service to add the patient to diagnosticTest");
			return new Message("Test added successfully");
		} else {
			logger.error("Patientdiagnostic Service: Exception : Diagnostic Not Found Exception");
			throw new DiagnosticNotFoundException();
		}

	}
}