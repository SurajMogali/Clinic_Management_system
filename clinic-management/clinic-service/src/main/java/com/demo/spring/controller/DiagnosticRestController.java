package com.demo.spring.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spring.dto.DiagnosticDTO;
import com.demo.spring.entity.Diagnostic;
import com.demo.spring.exceptions.DiagnosticNotFoundException;
import com.demo.spring.exceptions.DiagnosticTestExistsException;
import com.demo.spring.services.DiagnosticService;
import com.demo.spring.util.Message;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/clinic")
public class DiagnosticRestController {
	
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	@Autowired
	DiagnosticService diagnosticService;
	
	/**
	 * this method will return the list of all diagnosticTests
	 * @return
	 */
	@Timed(value = "requests.diagnostic.list")
	@GetMapping(path="/diagnostic/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Diagnostic>> listAllDiagnostics() {
		
		logger.info("The method had called the service to list the diagnostic tests");
		
		
			return ResponseEntity.ok(diagnosticService.listAll());
	}
	
	/**
	 * this method will add the new diagnosticTest
	 * @param diagnosticDto
	 * @return
	 * @throws DiagnosticTestExistsException
	 */
	@Timed(value = "requests.diagnostic.addtest")
	@PostMapping(path="/diagnostic/addTest" ,consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> addTest(@RequestBody DiagnosticDTO diagnosticDto) throws DiagnosticTestExistsException{
		
		logger.info("The method had called the service to add the diagnostic tests");
		
		return ResponseEntity.ok(diagnosticService.addTests(diagnosticDto));
	}
	
	/**
	 * this method will remove the diagnosticTest
	 * @param id
	 * @return
	 * @throws DiagnosticNotFoundException
	 */
	@Timed(value = "requests.diagnostic.delete")
	@DeleteMapping(path="/diagnostic/removeTest/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> removeTest(@PathVariable("id") Integer id) throws DiagnosticNotFoundException{
		logger.info("The method had called the service to delete the diagnostic test");
		
		
		
		
		return ResponseEntity.ok(diagnosticService.removeTests(id));
	}
}
