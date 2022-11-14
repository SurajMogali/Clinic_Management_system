package com.demo.spring.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.entity.Doctor;
import com.demo.spring.exceptions.DoctorNotFoundException;
import com.demo.spring.services.DoctorService;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/clinic")
public class DoctorRestController {
	
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Autowired
	DoctorService doctorService;
	
	/**
	 * this method will return list of all doctors
	 * @return
	 * @throws DoctorNotFoundException
	 */
	@Timed(value = "requests.doctor.list")
	@GetMapping(path="/doctor/list",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Doctor>> listDoctorData() throws DoctorNotFoundException{
		List<Doctor> doctorList = doctorService.listAllData();
		if(doctorList.isEmpty()) {
			
			throw new DoctorNotFoundException();
		}else {
			logger.info("The method had called the service to list the Doctors");
			return ResponseEntity.ok(doctorList);
		}
	}
	
	/**
	 * this method will return doctor by doctorId
	 * @param doctorId
	 * @return
	 * @throws DoctorNotFoundException
	 */
	@Timed(value = "requests.docotor.findbyid")
	@GetMapping(path = "/doctor/find/{doctorId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed(value = "requests.count.findbyid")
	public ResponseEntity<Doctor> findOneDoc(@PathVariable("doctorId") int doctorId) throws DoctorNotFoundException {
		
		logger.info("The method had called the service to find the Doctors by doctorId");

		return doctorService.findOneDocService(doctorId);

	}
	
	
	
	

}
