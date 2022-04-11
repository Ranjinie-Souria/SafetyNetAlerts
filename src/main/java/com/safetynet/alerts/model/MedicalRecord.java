package com.safetynet.alerts.model;

import java.util.List;

import lombok.Data;

@Data
public class MedicalRecord {
	private Person person;
	private String birthdate;
	private List<String> medications;
	private List<String> allergies;

}
