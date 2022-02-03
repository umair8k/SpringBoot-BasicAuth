package com.hc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hc.entity.Appointment;
import com.hc.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
    Optional<Patient> findByUserName(String username);
   // Appointment findByUserName(String userName);
	//Appointment findByPatientName(String userName);
}
