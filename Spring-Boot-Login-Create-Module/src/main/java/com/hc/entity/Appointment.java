package com.hc.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "APPOINTMENTS_RECORD")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {


	
	@Id
	@GeneratedValue
	private int appointmentId;
	private String disease;
	private String description;
	private String regTime;
	private String userName;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime appointmentDateAndTime;
	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;
	
	@Override
	public String toString() {
		return "Appointment(Child) [appointmentId=" + appointmentId + ", disease=" + disease + ", description=" + description
				+ ", regTime=" + regTime + ", userName=" + userName + ", appointmentDateAndTime="
				+ appointmentDateAndTime + ", status=" + status + "]";
	}
	

}
