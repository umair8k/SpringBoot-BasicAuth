package com.hc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "PATIENT_AUTHENTICATION_TBL")
public class Patient {
	@Id
	@GeneratedValue
	private int id;
	private String userName;
	private String password;
	private boolean active;
	private String roles;//ROLE_PATIENT,ROLE_NURSE
}
