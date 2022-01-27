package com.hc.controller;


import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hc.entity.Appointment;
import com.hc.entity.AppointmentStatus;
import com.hc.repository.AppointmentRepository;


@RestController
@RequestMapping("/post")
public class AppointmentController {


	@Autowired
	private AppointmentRepository apmntRepository;

	@PostMapping("/create")
	public String createApmnt(@RequestBody Appointment apmnt, Principal principal) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		Date current = new Date();  


		apmnt.setStatus(AppointmentStatus.PENDING);
		apmnt.setUserName(principal.getName());
		String time=current.toString();
		apmnt.setRegTime(time);
		apmntRepository.save(apmnt);
		return principal.getName() + " Your Appointment created successfully , Required Nurse/Doctor Action !";
	}

	@GetMapping("/approveAppointment/{appointId}")
	@PreAuthorize("hasAuthority('ROLE_DOCTOR')")
	public String approveApoint(@PathVariable int appointId) {
		Appointment apmnt = apmntRepository.findById(appointId).get();
		apmnt.setStatus(AppointmentStatus.APPROVED);
		apmntRepository.save(apmnt);
		return "Appointment Approved !!";
	}

	@GetMapping("/approveAll")
	@PreAuthorize("hasAuthority('ROLE_DOCTOR')")
	public String approveAll() {
		apmntRepository.findAll().stream().filter(appointment -> appointment.getStatus().equals(AppointmentStatus.PENDING)).forEach(appointment -> {
			appointment.setStatus(AppointmentStatus.APPROVED);
			apmntRepository.save(appointment);
		});
		return "Approved all Appointments !";
	}

	@GetMapping("/removeAppointment/{appointId}")
	@PreAuthorize("hasAuthority('ROLE_DOCTOR')")
	public String removeAppointment(@PathVariable int appointId) {
		Appointment apmnt = apmntRepository.findById(appointId).get();
		apmnt.setStatus(AppointmentStatus.REJECTED);
		apmntRepository.save(apmnt);
		return "Appointment Rejected !!";
	}


	@GetMapping("/rejectAll")
	@PreAuthorize("hasAuthority('ROLE_DOCTOR')")
	public String rejectAll() {
		apmntRepository.findAll().stream().filter(appointment -> appointment.getStatus().equals(AppointmentStatus.PENDING)).forEach(appointment -> {
			appointment.setStatus(AppointmentStatus.REJECTED);
			apmntRepository.save(appointment);
		});
		return "Rejected all appointments !";
	}

	@GetMapping("/viewAll")
	@PreAuthorize("hasAuthority('ROLE_NURSE') or hasAuthority('ROLE_DOCTOR') or hasAuthority('ROLE_ADMIN')")
	public  List<Appointment> viewAll(){
		return apmntRepository.findAll();/*.stream()
                .filter(post -> post.getStatus().equals(PostStatus.APPROVED))
                .collect(Collectors.toList());*/
	}
}
