package com.hc.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hc.common.PatientConstant;
import com.hc.entity.Appointment;
import com.hc.entity.Patient;
import com.hc.repository.AppointmentRepository;
import com.hc.repository.PatientRepository;

@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private PatientRepository repository;
	@Autowired
	private AppointmentRepository apmntRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostMapping("/signup")
	public String signUp(@RequestBody Patient patient) {
		patient.setRoles(PatientConstant.DEFAULT_ROLE);//Patient
		String encryptedPwd = passwordEncoder.encode(patient.getPassword());
		patient.setPassword(encryptedPwd);
		repository.save(patient);
		return "Hi " + patient.getUserName() + " welcome!";
	}

	@GetMapping("/access/{userId}/{patientRole}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String giveAccessToUser(@PathVariable int userId, @PathVariable String patientRole, Principal principal) {
		Patient patient = repository.findById(userId).get();
		List<String> activeRoles = getRolesByLoggedInUser(principal);
		String newRole = "";
		if (activeRoles.contains(patientRole)) {
			newRole = patient.getRoles() + "," + patientRole;
			patient.setRoles(newRole);
		}
		repository.save(patient);
		return "Hi " + patient.getUserName() + " New Role assign to you by " + principal.getName();
	}

	@GetMapping("/allUser")
	@Secured("ROLE_ADMIN")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Patient> loadUsers() {
		return repository.findAll();
	}

	@GetMapping("/test")
	@PreAuthorize("hasAuthority('ROLE_PATIENT')")
	public String test() {
	
		return "User can acces this url ";
			
	}

	private List<String> getRolesByLoggedInUser(Principal principal) {
		String roles = getLoggedInUser(principal).getRoles();
		List<String> assignRoles = Arrays.stream(roles.split(",")).collect(Collectors.toList());
		if (assignRoles.contains("ROLE_NURSE")) {
			return Arrays.stream(PatientConstant.NURSE_ACCESS).collect(Collectors.toList());
		}
		if (assignRoles.contains("ROLE_DOCTOR")) {
			return Arrays.stream(PatientConstant.DOCTOR_ACCESS).collect(Collectors.toList());
		}
		if (assignRoles.contains("ROLE_ADMIN")) {
			return Arrays.stream(PatientConstant.ADMIN_ACCESS).collect(Collectors.toList());
		}

		return Collections.emptyList();
	}

	private Patient getLoggedInUser(Principal principal) {
		return repository.findByUserName(principal.getName()).get();
	}
}
