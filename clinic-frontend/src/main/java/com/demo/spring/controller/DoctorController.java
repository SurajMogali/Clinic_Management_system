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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.demo.spring.dto.DoctorDTO;

@Controller
public class DoctorController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping(path = "/doctor/findDoctor")
	public ModelAndView findByDoctorId(@RequestParam(name = "doctorId", required = true) int doctorId) {
		ModelAndView mv = new ModelAndView();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Void> req = new HttpEntity<>(headers);
		ResponseEntity<DoctorDTO> response = restTemplate.exchange("http://localhost:8194/clinic/doctor/find/" + doctorId,
				HttpMethod.GET, req, DoctorDTO.class);
		if (response.getBody().getFirstName() != null) {
			mv.addObject("doctor", response.getBody());
			mv.setViewName("findDoctor");
		} else {
			ResponseEntity<String> response2 = restTemplate.exchange("http://localhost:8194/clinic/doctor/find/" + doctorId,
					HttpMethod.GET, req, String.class);
			mv.addObject("msg", response2.getBody());
			mv.setViewName("findPatientfailure");
		}

		return mv;

	}

	@GetMapping(path = "/doctor/listalldoctor")
	public ModelAndView listdoctors() {
		ModelAndView mv = new ModelAndView();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Void> req = new HttpEntity<>(headers);
		ResponseEntity<List<DoctorDTO>> response = restTemplate.exchange("http://localhost:8194/clinic/doctor/list",
				HttpMethod.GET, req, new ParameterizedTypeReference<List<DoctorDTO>>() {
				});
		mv.addObject("doctorlist", response.getBody());
		mv.setViewName("listDoctor");
		return mv;
	}

}