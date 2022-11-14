package com.demo.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {

	
	private Integer doctorId;
	private String firstName;
	private String lastName;
	private String email;


	
}
