package com.demo.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DOCTOR_SPECIALITY")
public class DoctorSpeciality {

	@Id
	@SequenceGenerator(sequenceName = "DoctorSpeciality_sequence", initialValue = 6, allocationSize = 1, name = "DoctorSpeciality_Id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DoctorSpeciality_Id")

	@Column(name = "id")
	private int id;

	@Column(name = "doctor_id")
	private Integer doctorId;

	@Column(name = "speciality_id")
	private Integer specialityId;

	public DoctorSpeciality() {

	}

	public DoctorSpeciality(Integer doctorId, Integer specialityId) {
		this.doctorId = doctorId;
		this.specialityId = specialityId;
	}

	public DoctorSpeciality(int id, Integer doctorId, Integer specialityId) {
		this.id = id;
		this.doctorId = doctorId;
		this.specialityId = specialityId;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
