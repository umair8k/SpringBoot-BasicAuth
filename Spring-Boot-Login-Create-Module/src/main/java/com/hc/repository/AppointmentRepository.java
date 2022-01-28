package com.hc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hc.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

	//Appointment findByPatientName(String userName);
	  List<Appointment> findByUserName(String username);

	

}
