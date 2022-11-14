package com.demo.spring.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring.dto.DiagnosticDTO;
import com.demo.spring.entity.Diagnostic;
import com.demo.spring.exceptions.DiagnosticNotFoundException;
import com.demo.spring.exceptions.DiagnosticTestExistsException;
import com.demo.spring.repository.DiagnosticRepository;
import com.demo.spring.util.Message;

@Service
public class DiagnosticService {
	
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Autowired
	DiagnosticRepository diagnosticRepository;
	
	public List<Diagnostic> listAll(){
		logger.info("Diagnostic Service: List the diagnostic tests");
		return diagnosticRepository.findAll();
	}
	
	public Message addTests(DiagnosticDTO diagnosticDTO) throws DiagnosticTestExistsException {
		logger.info("Diagnostic Service: Add the diagnostic tests");
		
		if(diagnosticRepository.existsById(diagnosticDTO.getDiagnosticId())) {
			throw new DiagnosticTestExistsException();
		}else {
			Diagnostic diagnostic = new Diagnostic(diagnosticDTO.getDiagnosticId(), diagnosticDTO.getDiagnosticName());
			diagnosticRepository.save(diagnostic);
			return new Message("Test added");
		}
	}
	
	public Message removeTests(Integer id) throws DiagnosticNotFoundException {
		logger.info("Diagnostic Service: Delete the diagnostic tests");
		Optional<Diagnostic> diagnostic = diagnosticRepository.findById(id);
		if(diagnostic.isPresent()) {
			diagnosticRepository.delete(diagnostic.get());
			return new Message("Test removed from test list");
		}else {
			logger.error("Diagnostic Service: Exception :Diagnostic Not Found Exception");
			throw new DiagnosticNotFoundException();
		}
	}

}
