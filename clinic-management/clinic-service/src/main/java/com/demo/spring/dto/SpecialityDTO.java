package com.demo.spring.dto;

public class SpecialityDTO {

	
	private Integer specialityId;
	private String specialityName;

	public SpecialityDTO() {
		
	}

	public SpecialityDTO(Integer specialityId, String specialityName) {
	
		this.specialityId = specialityId;
		this.specialityName = specialityName;
	}

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

	public String getSpecialityName() {
		return specialityName;
	}

	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}

}
