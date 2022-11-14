package com.demo.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.spring.entity.DoctorSpeciality;

public interface DoctorSpecialityRepository extends JpaRepository<DoctorSpeciality, Integer> {

	@Query("select d from DoctorSpeciality d where d.specialityId=:specialityId")
	public List<DoctorSpeciality> findAllDoctor(Integer specialityId);
	
	@Query("select d from DoctorSpeciality d where d.doctorId=:doctorId and d.specialityId=:specialityId")
    public List<DoctorSpeciality> findByDoctorIdAndSpecialityId(Integer doctorId,Integer specialityId);
	

	
	

}
