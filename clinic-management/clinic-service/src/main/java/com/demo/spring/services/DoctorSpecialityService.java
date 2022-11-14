package com.demo.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring.dto.DoctorSpecialityDTO;
import com.demo.spring.entity.Doctor;
import com.demo.spring.entity.DoctorSpeciality;
import com.demo.spring.exceptions.DoctorNotFoundException;
import com.demo.spring.exceptions.DoctorSpecialityNotFoundException;
import com.demo.spring.repository.DoctorRepository;
import com.demo.spring.repository.DoctorSpecialityRepository;
import com.demo.spring.util.Message;

@Service
public class DoctorSpecialityService {

	private Logger logger = LogManager.getLogger(this.getClass().getName());

	@Autowired
	DoctorSpecialityRepository doctorspecialityRepository;

	@Autowired
	DoctorRepository doctorRepository;

	public Message addDoctorService(DoctorSpecialityDTO doctorSpecialityDTO) throws DoctorNotFoundException {

		if (doctorRepository.existsById(doctorSpecialityDTO.getDoctorId())) {
			DoctorSpeciality doctorSpeciality = new DoctorSpeciality(doctorSpecialityDTO.getId(),
					doctorSpecialityDTO.getDoctorId(), doctorSpecialityDTO.getSpecialityId());
			doctorspecialityRepository.save(doctorSpeciality);
			logger.info("DoctorSpeciality Service: Add the Doctor to Speciality");
			return new Message("Doctor added to speciality");
		} else {

			logger.error("DoctorSpeciality Service: Exception : Doctor Not Found Exception");
			throw new DoctorNotFoundException();
		}

	}

	public Message removeDoctorFromSpecialityService(Integer doctorId, Integer specialityId)
			throws DoctorSpecialityNotFoundException {
		List<DoctorSpeciality> doctorList = doctorspecialityRepository.findByDoctorIdAndSpecialityId(doctorId,
				specialityId);
		if (doctorList.isEmpty()) {
			throw new DoctorSpecialityNotFoundException();
		} else {
			for (DoctorSpeciality doctorSpeciality : doctorList) {
				doctorspecialityRepository.delete(doctorSpeciality);
			}
			return new Message("Doctor Removed from Speciality");
		}

	}

	public List<Doctor> listDoctorInSpeciality(Integer specialityID)
			throws DoctorSpecialityNotFoundException, DoctorNotFoundException {
		List<DoctorSpeciality> doctorIdList = doctorspecialityRepository.findAllDoctor(specialityID);
		Integer i = 0;
		List<Doctor> doctorList = new ArrayList<>();
		if (doctorIdList.isEmpty()) {
			logger.error("DoctorSpeciality Service: Exception : Doctor Not Found Exception");
			throw new DoctorNotFoundException();
		} else {
			for (doctorRepository.existsById(doctorIdList.get(i).getDoctorId()); i < doctorIdList.size(); i++) {

				Optional<Doctor> doctorOps = doctorRepository.findById(doctorIdList.get(i).getDoctorId());
				if (doctorOps.isPresent()) {
					doctorList.add(doctorOps.get());
				}
			}
			if (doctorList.isEmpty()) {
				logger.error("DoctorSpeciality Service: Exception : DoctorSpeciality Not Found Exception");
				throw new DoctorSpecialityNotFoundException();
			} else {
				logger.info("DoctorSpeciality Service: List the Doctors with Speciality ");
				return doctorList;
			}
		}
	}

}
