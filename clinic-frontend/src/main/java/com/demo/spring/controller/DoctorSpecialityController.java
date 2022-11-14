package com.demo.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.demo.spring.dto.AppointmentDTO;
import com.demo.spring.dto.DoctorDTO;
import com.demo.spring.dto.DoctorSpecialityDTO;
import com.demo.spring.dto.PatientDTO;

@Controller
public class DoctorSpecialityController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping(path = "/doctorSpeciality/listdoctorinspeciality")
	public ModelAndView listDoctorInSpeciality(
			@RequestParam(name = "specialityId", required = true) String specialityId) {
		ModelAndView mv = new ModelAndView();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Void> request = new HttpEntity<>(headers);
		String str= restTemplate.exchange(
				"http://localhost:8194/clinic/speciality/list/" + specialityId, HttpMethod.GET, request,
				String.class).getBody(); 
		System.out.println(str);

		if (str != null && str.equals("{\"status\":\"Doctor data is not available\"}")) {
			mv.addObject("msg", "Speciality Not Found");
			mv.setViewName("savePatientfailure");
		} else {
			ResponseEntity<List<DoctorDTO>> response=restTemplate.exchange(
					"http://localhost:8194/clinic/speciality/list/" + specialityId, HttpMethod.GET, request,
					new ParameterizedTypeReference<List<DoctorDTO>>() {
					});
			
			
			mv.addObject("doctorList", response.getBody());
			mv.setViewName("listDoctorSpeciality");
		}
		return mv;
	}

	@PostMapping(path = "/doctorSpeciality/addDoctorToSpeciality")
	public ModelAndView addDoctorSpeciality(DoctorSpecialityDTO doctorSpecialityDTO) {
		ModelAndView mv = new ModelAndView();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<DoctorSpecialityDTO> req = new HttpEntity<>(doctorSpecialityDTO, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8194/clinic/speciality/addDoctor",
				HttpMethod.POST, req, String.class);
		mv.setViewName("saveDoctorSpeciality");
		mv.addObject("msg", response.getBody());
		return mv;
	}

	@PostMapping(path = "/doctorSpeciality/removedoctor")
	public ModelAndView findById(@RequestParam(name = "doctorId", required = true) int doctorId,
			@RequestParam(name = "specialityId", required = true) int specialityId) {
		System.out.println(doctorId+" "+specialityId);
		ModelAndView mv = new ModelAndView();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Void> req = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange(
				"http://localhost:8194/clinic/speciality/removeDoctor/" + doctorId + "/" + specialityId,
				HttpMethod.DELETE, req, String.class);
		
		mv.setViewName("deleteDoctorSpeciality");
		mv.addObject("msg", response.getBody());
		return mv;

	}
}