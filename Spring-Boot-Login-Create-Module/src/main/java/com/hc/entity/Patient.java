package com.hc.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PATIENT_AUTHENTICATION_TBL")
public class Patient {
    @Id
    @GeneratedValue
    @Column(name="user_id")
    private int id;
    private String userName;
    private String password;
    private boolean active;
    private String roles;//ROLE_PATIENT,ROLE_NURSE
    
    @OneToMany(targetEntity=Appointment.class,cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",referencedColumnName="user_id")
    private List<Appointment> appointment;

	@Override
	public String toString() {
		return "Patient (Parent)[id=" + id + ", userName=" + userName + ", password=" + password + ", active=" + active
				+ ", roles=" + roles + "]";
	}
    
    
}
