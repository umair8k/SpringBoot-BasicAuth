package com.hc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hc.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
}
