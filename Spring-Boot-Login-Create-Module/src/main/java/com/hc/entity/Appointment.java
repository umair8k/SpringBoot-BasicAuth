package com.hc.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    private String userName;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
    
    
	@Override
	public String toString() {
		return "Appointment (Child)[appointmentId=" + appointmentId + ", disease=" + disease + ", description=" + description
				+ ", userName=" + userName + ", status=" + status + "]";
	}
    
    

}
