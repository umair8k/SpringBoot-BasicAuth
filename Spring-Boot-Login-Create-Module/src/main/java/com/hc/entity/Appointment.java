package com.hc.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "APPOINTMENTS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Appointment {

	@Id
	@GeneratedValue
	private int appointmentId;
	private String disease;
	private String description;
	private String regTime;
	private String userName;
	private LocalDateTime appointmentDateAndTime;
	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;

}
