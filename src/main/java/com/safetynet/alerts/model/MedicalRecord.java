package com.safetynet.alerts.model;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Component
@RequiredArgsConstructor
public class MedicalRecord {
	public MedicalRecord(Person person, String birthdate, List<String> medications, List<String> allergies) {
		this.person = person;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
	}
	private Person person;
	private String birthdate;
	private List<String> medications;
	private List<String> allergies;
	

}
