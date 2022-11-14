package com.demo.spring.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.demo.spring.dto.AppointmentDTO;
import com.demo.spring.dto.DoctorDTO;
import com.demo.spring.dto.PatientDTO;
import com.demo.spring.entity.Appointment;
import com.demo.spring.entity.Message;
import com.demo.spring.exception.AppointmentNotFoundException;
import com.demo.spring.exceptions.DoctorNotFoundException;
import com.demo.spring.exceptions.PatientNotExistsException;
import com.demo.spring.exceptions.PatientNotFoundException;
import com.demo.spring.services.AppointmentService;
import com.demo.spring.util.ServerConfiguration;

import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.OpenAPI30;




@RestController
@EnableConfigurationProperties(ServerConfiguration.class)
@RequestMapping("/appointment")
@OpenAPI30
public class AppointmentRestController {
	
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	@Autowired
	AppointmentService appointmentService;
	
	/**
	 * this method will return list of all appointments
	 * @return
	 */
	
	

	@Timed(value = "requests.count.list")
	@GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> findAllAppointments() {
		logger.info("This method had called the service to list appointments");
		return (appointmentService.findAllAppointmentsService());
	}

	
	
	/**
	 * this method will return appointmentList by date
	 * @param doctorID
	 * @param date
	 * @return
	 * @throws AppointmentNotFoundException
	 */
	@Timed(value = "requests.count.listbydate")
	@GetMapping(path = "/listbyDate/{doctorID}/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> findAppointmentsByDate(@PathVariable("doctorID") int doctorID,
			@PathVariable("date") String date) throws AppointmentNotFoundException {
		logger.info("This method had called the service to list Appointments by date");
		
		return appointmentService.findAppointmentsByDateService(doctorID, date);
	}


	
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ServerConfiguration server;
	
	/**
	 * this method will save all appointments
	 * @param appointmentDTO
	 * @return
	 * @throws PatientNotExistsException
	 * @throws DoctorNotFoundException
	 * @throws NullPointerException
	 */
	
	@PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Timed(value = "request.add.appointment")
	public ResponseEntity<Message> addAppointment(@RequestBody AppointmentDTO appointmentDTO)
			throws PatientNotExistsException, DoctorNotFoundException, NullPointerException {
		
		logger.info("This method had called the service to save appointments");
		
		PatientDTO patientDTO = restTemplate.getForEntity("http://patient-service/patient/{id}", PatientDTO.class,
				appointmentDTO.getPatientId()).getBody();

		if (patientDTO != null && patientDTO.getPatientId() != null
				&& patientDTO.getPatientId().equals(appointmentDTO.getPatientId())) {
			DoctorDTO doctorDTO = restTemplate.getForEntity("http://clinic-service/clinic/doctor/find/{doctorId}",
					DoctorDTO.class, appointmentDTO.getDoctorId()).getBody();

			if (doctorDTO != null && doctorDTO.getDoctorId() != null
					&& doctorDTO.getDoctorId().equals(appointmentDTO.getDoctorId())) {
				return appointmentService.getAppointmentService(appointmentDTO);
			} else {
				logger.error("Exception : Doctor Not Found Exception");
				throw new DoctorNotFoundException();
			}

		} else {
			logger.error("Exception : Patient Not Found Exception");
			throw new PatientNotExistsException();
		}

	}


	
	
	

}
