package com.demo.spring;




import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ThymeleafFrontApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ThymeleafFrontApplication.class, args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		registry.addViewController("/login/home").setViewName("home");
		
		registry.addViewController("patientfind").setViewName("findPatient");
		registry.addViewController("patientsave").setViewName("savePatient");
		registry.addViewController("patientlist").setViewName("listPatient");
		registry.addViewController("patientupdate").setViewName("updatePatient");
		registry.addViewController("/patient").setViewName("homePatient");
		
		
		registry.addViewController("appointmentlistdate").setViewName("listAppointmentDate");
		registry.addViewController("appointmentsave").setViewName("saveAppointment");
		registry.addViewController("/appointment").setViewName("homeAppointment");
		
		
		registry.addViewController("/diagnostic/add").setViewName("saveDiagnostic");
        registry.addViewController("/diagnostic/list").setViewName("listDiagnostic");
        registry.addViewController("/diagnostic/delete1").setViewName("deleteDiagnostic");
        registry.addViewController("/diagnostic").setViewName("homeDiagnostic");
        
        registry.addViewController("/doctor/find").setViewName("findDoctor");
        registry.addViewController("/doctor/list").setViewName("listDoctor");
        registry.addViewController("/doctor").setViewName("homeDoctor");
        
        registry.addViewController("/doctorSpeciality/list").setViewName("listDoctorSpeciality");
        registry.addViewController("/doctorSpeciality/add").setViewName("saveDoctorSpeciality");
        registry.addViewController("/doctorSpeciality/remove").setViewName("deleteDoctorSpeciality");
        
        registry.addViewController("/diagnostic/addTestPatient").setViewName("saveDiagnosticPatient");
        
        registry.addViewController("/error").setViewName("error");
        
        registry.addViewController("/").setViewName("login");
        
        registry.addViewController("/findPatientfailure").setViewName("findPatientfailure");
        
        
        
        
        
        
        
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	

	
	
	
	

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		HttpClient httpClient = HttpClientBuilder.create().build();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));

		return restTemplate;
	}

}
