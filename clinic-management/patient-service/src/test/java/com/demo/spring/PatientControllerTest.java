package com.demo.spring;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.spring.entity.Patient;
import com.demo.spring.repository.PatientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PatientControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	PatientRepository patientRepository;

	@Autowired
	TestRestTemplate template;

	@Test
	void testSavePatientSuccess() throws Exception {
		Patient patient = new Patient(200, "suraj", "mogali", "surajmogali@gmail.com");
		ObjectMapper mapper = new ObjectMapper();
		String patientJson = mapper.writeValueAsString(patient);
		when(patientRepository.existsById(300)).thenReturn(false);
		mvc.perform(post("/patient/save").content(patientJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("Patient saved"));
	}

	@Test
	void testSavePatientFailure() throws Exception {
		Patient patient = new Patient(20, "Jean", "logan", "jean@gmail.com");
		ObjectMapper mapper = new ObjectMapper();
		String patientJson = mapper.writeValueAsString(patient);
		when(patientRepository.existsById(20)).thenReturn(true);
		mvc.perform(post("/patient/save").content(patientJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("Patient already exists"));
	}

	@Test
	void testfindPatientSuccess() throws Exception {

		Patient patient = new Patient(200, "suraj", "mogali", "surajmogali@gmail.com");
		when(patientRepository.findById(200)).thenReturn(Optional.of(patient));
		mvc.perform(get("/patient/200")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.firstName").value("suraj"));

	}

	@Test
	void testfindPatientFailure() throws Exception {

		when(patientRepository.findById(100)).thenReturn(Optional.empty());
		mvc.perform(get("/patient/100")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("No Patient Found"));

	}
	
	@Test
	void testListByPatientName() throws Exception {
		List<Patient> patientList = new ArrayList<>();
		patientList.addAll(patientList);
		ObjectMapper mapper = new ObjectMapper();
		String patientJson = mapper.writeValueAsString(patientList);
		when(patientRepository.findAllByFirstName("suraj")).thenReturn(patientList);
		
		mvc.perform(get("/patient/list/suraj"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
//			.andExpect(content().json(patientJson));

	}
	
	@Test
	void testListByPatientNameFailure() throws Exception {
		List<Patient> patientList = new ArrayList<>();
		when(patientRepository.findAllByFirstName("suraj")).thenReturn(patientList);
		
		mvc.perform(get("/patient/list/suraj"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.status").value("No Patient Found"));

	}
	
	
	
	
	
	

	@Test
	void testUpdatePatientSuccess() throws Exception {
		Patient patient = new Patient(20, "suraj", "mogali", "surajmogali@gmail.com");
		ObjectMapper mapper = new ObjectMapper();
		String patientJson = mapper.writeValueAsString(patient);
		when(patientRepository.existsById(20)).thenReturn(true);
		mvc.perform(patch("/patient/update").content(patientJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("Patient details has been updated !!!"));
	}

	@Test
	void testUpdatePatientFailure() throws Exception {
		Patient patient = new Patient(200, "Jean", "logan", "jean@gmail.com");
		ObjectMapper mapper = new ObjectMapper();
		String patientJson = mapper.writeValueAsString(patient);
		when(patientRepository.existsById(200)).thenReturn(false);
		mvc.perform(patch("/patient/update").content(patientJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("Patient not found"));
	}

}