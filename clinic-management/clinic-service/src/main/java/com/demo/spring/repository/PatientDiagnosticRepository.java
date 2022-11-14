package com.demo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.spring.entity.PatientDiagnostic;

public interface PatientDiagnosticRepository extends JpaRepository<PatientDiagnostic, Integer> {

}