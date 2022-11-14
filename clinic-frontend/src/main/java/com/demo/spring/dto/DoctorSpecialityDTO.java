package com.demo.spring.dto;

public class DoctorSpecialityDTO {

	private int id;
	private Integer doctorId;
	private Integer specialityId;

	public DoctorSpecialityDTO() {

	}

	public DoctorSpecialityDTO(Integer doctorId, Integer specialityId) {
		super();
		this.doctorId = doctorId;
		this.specialityId = specialityId;
	}

	public int getId() {
		return id;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

}