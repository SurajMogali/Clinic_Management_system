package com.demo.spring;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.spring.entity.Doctor;
import com.demo.spring.entity.DoctorSpeciality;
import com.demo.spring.repository.DoctorRepository;
import com.demo.spring.repository.DoctorSpecialityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DoctorSpecialityRestControllerTest {

	@Autowired
	MockMvc mvc;
	
	@MockBean
	DoctorSpecialityRepository repository;
	
	@MockBean
	DoctorRepository doctorRepository;
	
	DoctorSpeciality doctorSpeciality = new DoctorSpeciality(101, 1);
	
	@Test
	void testListAllDoctor() throws Exception {
		Doctor doctor = new Doctor(101, "suraj", "mogali", "surajmogali@gmail.com");
		List<Doctor> doctorList = new ArrayList<>();
		doctorList.add(doctor);
		List<DoctorSpeciality> spList = new ArrayList<>();
		spList.add(doctorSpeciality);
		ObjectMapper mapper = new ObjectMapper();
		String doctorJson = mapper.writeValueAsString(doctorList);
		when(repository.findAllDoctor(1)).thenReturn(spList);
		when(doctorRepository.findById(101)).thenReturn(Optional.of(doctor));
		
		mvc.perform(get("/clinic/speciality/list/1"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(content().json(doctorJson));
	}
	
	@Test
	void testListAllDoctorfailure() throws Exception {
		DoctorSpeciality doctorSpeciality = new DoctorSpeciality(101, 1);
		List<DoctorSpeciality> spList = new ArrayList<>();
		spList.add(doctorSpeciality);
		when(repository.findAllDoctor(1)).thenReturn(spList);
		when(doctorRepository.findById(101)).thenReturn(Optional.empty());
		
		mvc.perform(get("/clinic/speciality/list/1"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.status").value("Doctor do not have speciality"));
	}

	@Test
	void testAddDoctorTOSpeciality() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String doctorJson = mapper.writeValueAsString(doctorSpeciality);
		when(doctorRepository.existsById(doctorSpeciality.getDoctorId())).thenReturn(true);
		when(repository.save(doctorSpeciality)).thenReturn(doctorSpeciality);
		
		mvc.perform(post("/clinic/speciality/addDoctor").content(doctorJson).contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.status").value("Doctor added to speciality"));
	}
	
	@Test
	void testAddDoctorTOSpecialityFailure() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String doctorJson = mapper.writeValueAsString(doctorSpeciality);
		when(doctorRepository.existsById(doctorSpeciality.getDoctorId())).thenReturn(false);
		
		mvc.perform(post("/clinic/speciality/addDoctor").content(doctorJson).contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(jsonPath("$.status").value("Doctor data is not available"));
	}
	
	@Test
    void testRemoveDoctorSuccess() throws Exception {
        DoctorSpeciality doctorSpeciality = new DoctorSpeciality(105, 5);
        List<DoctorSpeciality> doctorSpecialityList = new ArrayList<>();
        doctorSpecialityList.add(doctorSpeciality);
        when(repository.findByDoctorIdAndSpecialityId(105, 5)).thenReturn(doctorSpecialityList);



       mvc.perform(delete("/clinic/speciality/removeDoctor/105/5")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.status").value("Doctor Removed from Speciality"));
    }



   @Test
    void testRemoveDoctorfailure() throws Exception {
        when(repository.findById(101)).thenReturn(Optional.empty());



       mvc.perform(delete("/clinic/speciality/removeDoctor/101/4")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.status").value("Doctor do not have speciality"));
    }

}
