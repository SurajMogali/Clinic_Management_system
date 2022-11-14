package com.demo.spring.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.dto.PatientDTO;
import com.demo.spring.entity.Message;
import com.demo.spring.entity.Patient;
import com.demo.spring.exceptions.PatientNotExistsException;
import com.demo.spring.exceptions.PatientNotFoundException;
import com.demo.spring.repository.PatientRepository;
import com.demo.spring.services.PatientService;

import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.OpenAPI30;

@OpenAPI30
@RestController
@RequestMapping("/patient")
public class PatientRestController {
	
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	PatientService patientService;
	
	/**
	 * this method will return patient by patientId
	 * @param patientId
	 * @return
	 * @throws PatientNotExistsException
	 */

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed(value = "requests.count.findbyid")
	public ResponseEntity<Patient> findOnePat(@PathVariable("id") int patientId) throws PatientNotExistsException {
		
		logger.info("The method had called the service to list patients by patientId");
		

		return patientService.findOnePatService(patientId);

	}
	
	
	/**
	 * this method will save the new patient
	 * @param patientDTO
	 * @return
	 */
 
	@Timed(value = "requests.count.save")
	@PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> savePatient(@RequestBody PatientDTO patientDTO) {
		
		logger.info("The method had called the service to save patients");

		return patientService.savePatientService(patientDTO);

	}

//	@GetMapping(path = "/list/{fname}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<Patient>> getAllByfName(@PathVariable("fname") String fname) {
//		return ResponseEntity.ok(patientRepository.findAllByFirstName(fname));
//	}
	
	
	/**
	 * this method will return list of patients by firstName
	 * @param fname
	 * @return
	 * @throws PatientNotFoundException
	 */

	@Timed(value = "requests.count.findbyname")
	@GetMapping(path = "/list/{fname}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Patient>> listByPatientName(@PathVariable("fname") String fname)
			throws PatientNotFoundException {
		logger.info("The method had called the service to list patients");

		return patientService.listByPatientNameService(fname);

	}

	
	/**
	 * this method will update the patient details
	 * @param patientDto
	 * @return
	 */
	@Timed(value = "requests.count.update")
	@PatchMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> updatePatient(@RequestBody PatientDTO patientDto) {
		if (patientRepository.existsById(patientDto.getPatientId())) {
			logger.info("The method had called the service to update Patient Details");
			Patient patient = new Patient(patientDto.getPatientId(), patientDto.getFirstName(),
					patientDto.getLastName(), patientDto.getEmail());
			patientRepository.save(patient);
			return ResponseEntity.ok(new Message("Patient details has been updated !!!"));
		} else {
			logger.error( "Exception : Patient Not Found Exception");
			return ResponseEntity.ok(new Message("Patient not found"));
		}
	}

}
