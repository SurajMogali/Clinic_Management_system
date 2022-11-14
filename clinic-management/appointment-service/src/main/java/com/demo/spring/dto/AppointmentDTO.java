package com.demo.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
	private Integer appointmentId;
	
	private Integer doctorId;
	
	private Integer patientId;
	
	private String date;

	
	
}
