package com.demo.spring.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.demo.spring.dto.AppointmentDTO;

public class AppointmentTest {
	
	@Test
	void testAppointmentDTO() {
		Appointment appointment=new Appointment();
		
		Appointment appointment2=new Appointment(2,201,101,"2022-10-20");
		
		appointment.setAppointmentId(2);
		appointment.setDate("2022-10-20");
		appointment.setDoctorId(101);
		appointment.setPatientId(201);
		
		assertEquals(2,appointment.getAppointmentId());
		assertEquals("2022-10-20",appointment.getDate());
		assertEquals(101,appointment.getDoctorId());
		assertEquals(201,appointment.getPatientId());
		
		
		
		
		
		
	}

}
