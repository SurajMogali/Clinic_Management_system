package com.demo.spring.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.dto.DoctorSpecialityDTO;
import com.demo.spring.entity.Doctor;
import com.demo.spring.exceptions.DoctorNotFoundException;
import com.demo.spring.exceptions.DoctorSpecialityNotFoundException;
import com.demo.spring.services.DoctorSpecialityService;
import com.demo.spring.util.Message;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/clinic")
public class DoctorSpecialityRestController {
	
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	@Autowired
	DoctorSpecialityService specialityService;

	/**
	 * this method will return list of doctor by specilaityId
	 * @param id
	 * @return
	 * @throws DoctorSpecialityNotFoundException
	 * @throws DoctorNotFoundException
	 */
	@Timed(value = "requests.speciality.findbyid")
	@GetMapping(path = "/speciality/list/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Doctor>> listAllDoctor(@PathVariable("id") Integer id) throws DoctorSpecialityNotFoundException, DoctorNotFoundException {
		List<Doctor> doctorList =specialityService.listDoctorInSpeciality(id);
		if(doctorList.isEmpty()) {
			throw new DoctorSpecialityNotFoundException();
		}else {
			logger.info("The method had called the service to list the speciality by specialityId");
			return ResponseEntity.ok(doctorList);
		}
		
	}
	
	/**
	 * this method will add the doctor to specialityId
	 * @param doctorSpecialityDTO
	 * @return
	 * @throws DoctorNotFoundException
	 */
	@Timed(value = "requests.speciality.addDoctor")
	@PostMapping(path="/speciality/addDoctor" ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> addDoctorToSpeciality(@RequestBody DoctorSpecialityDTO doctorSpecialityDTO) throws DoctorNotFoundException{
		
		logger.info("The method had called the service to add Doctors to Speciality");
 
        return ResponseEntity.ok(specialityService.addDoctorService(doctorSpecialityDTO));
    }
	
	@Timed(value = "requests.speciality.remove")
	@DeleteMapping(path = "/speciality/removeDoctor/{doctorId}/{specialityId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> removeDoctorFromSpeciality(@PathVariable("doctorId") Integer doctorId,@PathVariable("specialityId") Integer specialityId) throws DoctorSpecialityNotFoundException {
        return ResponseEntity.ok(specialityService.removeDoctorFromSpecialityService(doctorId,specialityId));
    }
	

}
