package com.safetynet.alerts.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@Component
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PersonAndMedicalInfo extends Person{
	
	private String firstName;
	private String lastName;
	private String address;
	private int age;
	private String phone;
	private List<String> medications;
	private List<String> allergies;
	
	public PersonAndMedicalInfo(Person person, MedicalRecord medicalRecord) {
		
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.address = person.getAddress();
		this.phone = person.getPhone();
		this.medications = medicalRecord.getMedications();
		this.allergies = medicalRecord.getAllergies();	
		this.age = this.getAge(medicalRecord);
	}
	
	public int getAge(MedicalRecord mR){
		String birthdate = mR.getBirthdate();
		String birthyear = birthdate.substring(birthdate.length() - 4);
		LocalDate currentdate = LocalDate.now();
		int currentYear = currentdate.getYear();		
		return currentYear - Integer.parseInt(birthyear);
	}

}
