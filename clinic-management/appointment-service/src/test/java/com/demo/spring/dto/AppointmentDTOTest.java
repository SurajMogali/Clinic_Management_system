package com.demo.spring.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AppointmentDTOTest {
	
	@Test
	void testAppointmentDTO() {
		AppointmentDTO appointmentDto=new AppointmentDTO();
		
		AppointmentDTO appointmentDto2=new AppointmentDTO(2,201,101,"2022-10-20");
		
		appointmentDto.setAppointmentId(2);
		appointmentDto.setDate("2022-10-20");
		appointmentDto.setDoctorId(101);
		appointmentDto.setPatientId(201);
		
		assertEquals(2,appointmentDto.getAppointmentId());
		assertEquals("2022-10-20",appointmentDto.getDate());
		assertEquals(101,appointmentDto.getDoctorId());
		assertEquals(201,appointmentDto.getPatientId());
		
		
		
		
		
		
	}

}
