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

import com.demo.spring.dto.PatientDTO;

@Controller

public class PatientController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping(path = "/findPatient")
	public ModelAndView findById(@RequestParam(name = "id", required = true) int id) {
		ModelAndView mv = new ModelAndView();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Void> req = new HttpEntity<>(headers);
		ResponseEntity<PatientDTO> response = restTemplate.exchange("http://localhost:8194/patient/" + id,
				HttpMethod.GET, req, PatientDTO.class);
		if (response.getBody().getFirstName() != null) {
            mv.addObject("patient", response.getBody());
            mv.setViewName("findPatient");
        } else {
            ResponseEntity<String> response2 = restTemplate.exchange("http://localhost:8194/patient/" + id,
                    HttpMethod.GET, req, String.class);
            mv.addObject("msg", response2.getBody());
            mv.setViewName("findPatientfailure");
        }
		
		return mv;

	}

	@GetMapping(path = "/listP")
	public ModelAndView findAll(@RequestParam(name = "firstName", required = true) String firstName) {
		ModelAndView mv = new ModelAndView();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Void> req = new HttpEntity<>(headers);
		
		String str= restTemplate.exchange(
				"http://localhost:8194/patient/list/" + firstName, HttpMethod.GET, req,
				String.class).getBody();
		System.out.println(str);
		if (str!= null && str.equals("{\"status\":\"No Patient Found\"}")) {
			mv.addObject("response", "No Patient Found" );
			mv.setViewName("listPatient");
        } else {
        	ResponseEntity<List<PatientDTO>> response = restTemplate.exchange(
    				"http://localhost:8194/patient/list/" + firstName, HttpMethod.GET, req,
    				new ParameterizedTypeReference<List<PatientDTO>>() {
    				});
            
            mv.addObject("patientList", response.getBody());
            mv.setViewName("listPatient");
        }
		
		return mv;
	}

	@PostMapping(path = "/savePatient")
	public ModelAndView savePatient(PatientDTO patientDTO) {
		ModelAndView mv = new ModelAndView();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<PatientDTO> req = new HttpEntity<>(patientDTO, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8194/patient/save", HttpMethod.POST,
				req, String.class);

		mv.addObject("msg", response.getBody());
		mv.setViewName("savePatientsuccess");
		return mv;
	}

	@PostMapping(path = "/updatePatient")
	public ModelAndView updatePatient(PatientDTO patientDTO) {
		ModelAndView mv = new ModelAndView();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<PatientDTO> req = new HttpEntity<>(patientDTO, headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8194/patient/update",
				HttpMethod.PATCH, req, String.class);
		if (response.getBody()!= null) {
			mv.addObject("msg", response.getBody());
			mv.setViewName("updatePatientsuccess");
			
        } else {
        	ResponseEntity<String> response2 = restTemplate.exchange("http://localhost:8194/patient/update",
    				HttpMethod.PATCH, req, String.class);
           
            mv.addObject("msg", response2.getBody());
            mv.setViewName("updatePatientfailure");
        }
		
		return mv;
		
		
		
	}

}
