package com.demo.spring.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.spring.dto.PatientDTO;
import com.demo.spring.entity.Message;
import com.demo.spring.entity.Patient;
import com.demo.spring.exceptions.PatientNotExistsException;
import com.demo.spring.exceptions.PatientNotFoundException;
import com.demo.spring.repository.PatientRepository;

@Service
public class PatientService {
	
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	@Autowired
	PatientRepository patientRepository;

	public ResponseEntity<Patient> findOnePatService(int patientId) throws PatientNotExistsException {
		Optional<Patient> patOp = patientRepository.findById(patientId);
		if (patOp.isPresent()) {
			
			logger.info("Patient Service: Find the patient by patientId");
			return ResponseEntity.ok(patOp.get());
		} else {
			logger.error("Patient Service: Exception : Patient Not Found Exception");
			throw new PatientNotExistsException();
			

		}

	}

	public ResponseEntity<Message> savePatientService(PatientDTO patientDTO) {
		if (patientRepository.existsById(patientDTO.getPatientId())) {
			
			logger.error("Patient Service: Exception : Patient Already Exists Exception ");
			return ResponseEntity.ok(new Message("Patient already exists"));
		} else {
			Patient patient = new Patient(patientDTO.getPatientId(), patientDTO.getFirstName(),
					patientDTO.getLastName(), patientDTO.getEmail());
			patientRepository.save(patient);
			logger.info("Patient Service: Save the Patients ");
			
			return ResponseEntity.ok(new Message("Patient saved"));
		}

	}
	
	
	public ResponseEntity<List<Patient>> listByPatientNameService(String fname) throws PatientNotFoundException {
		List<Patient> patientList = patientRepository.findAllByFirstName(fname);
		if (patientList.isEmpty()) {
			logger.error("Patient Service: Exception : Patient Not Found Exception");
			
			throw new PatientNotFoundException();
		} else {
			
			logger.info("Patient Service - List the patients based on firstName");
			return ResponseEntity.ok(patientList);
		}

	}

}
