package com.demo.spring.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.spring.entity.Doctor;
import com.demo.spring.exceptions.DoctorNotFoundException;
import com.demo.spring.repository.DoctorRepository;

@Service
public class DoctorService {
	
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	
	@Autowired
	DoctorRepository doctorRepository;
	
	public List<Doctor> listAllData(){
		
		logger.info("Doctor Service: List all the Doctors");
		
		return doctorRepository.findAll();
	}
	
	public ResponseEntity<Doctor> findOneDocService(int doctorId) throws DoctorNotFoundException {
		Optional<Doctor> docOp = doctorRepository.findById(doctorId);
		if (docOp.isPresent()) {
			logger.info("Docotor Service: Find the Doctors by doctorId");
			return ResponseEntity.ok(docOp.get());
		} else {
			logger.error("Docotor Service: Exception : Doctor Not Found Exception");
			throw new DoctorNotFoundException();

		}

	}

}
