package com.demo.spring.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.spring.dto.AppointmentDTO;
import com.demo.spring.dto.DoctorDTO;
import com.demo.spring.dto.PatientDTO;
import com.demo.spring.entity.Appointment;
import com.demo.spring.entity.Message;
import com.demo.spring.exception.AppointmentNotFoundException;
import com.demo.spring.exceptions.DoctorNotFoundException;
import com.demo.spring.exceptions.PatientNotFoundException;
import com.demo.spring.repository.AppointmentRepository;

import io.micrometer.core.annotation.Timed;

@Service
public class AppointmentService {
	
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	@Autowired
	AppointmentRepository appointmentRepository;

	public ResponseEntity<List<Appointment>> findAllAppointmentsService() {
		logger.info("Appointment Service: List all the appointments");
		return ResponseEntity.ok(appointmentRepository.findAll());
	}

	public ResponseEntity<List<Appointment>> findAppointmentsByDateService(int doctorID, String date)
			throws AppointmentNotFoundException {
		List<Appointment> listAppointment = appointmentRepository.findAllByDate(doctorID, date);
		logger.info("Appointment Service: List the appointments based on date");
		if (listAppointment.isEmpty()) {
			
			logger.error("Appointment Service: Exception : Appointment Not Found Exception");
			throw new AppointmentNotFoundException();
		} else {
			return ResponseEntity.ok(listAppointment);
		}
	}

	public ResponseEntity<Message> getAppointmentService(AppointmentDTO appointmentDTO) {
		logger.info("Appointment Service: List the appointments based on date");
		if (appointmentRepository.existsById(appointmentDTO.getAppointmentId())) {
			
			return ResponseEntity.ok(new Message("Appointment already exists"));
		} else {
			Appointment appointment = new Appointment(appointmentDTO.getAppointmentId(), appointmentDTO.getDoctorId(),
					appointmentDTO.getPatientId(), appointmentDTO.getDate());
			appointmentRepository.save(appointment);
			return ResponseEntity.ok(new Message("Appointment saved"));
		}

	}
	
	
    



}	
	


