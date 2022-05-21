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
		this.zip = person.getZip();
		this.city = person.getCity();
		this.email = person.getEmail();
		this.medications = medicalRecord.getMedications();
		this.allergies = medicalRecord.getAllergies();	
		this.age = this.getAge(medicalRecord);
	}
	
	
	public PersonAndMedicalInfo(MedicalRecord mR) {
		this.age = this.getAge(mR);
		Person entry = mR.getPerson();
		this.firstName = entry.getFirstName();
		this.lastName = entry.getLastName();
		this.address = entry.getAddress();
		this.city = entry.getCity();
		this.zip = entry.getZip();
		this.phone = entry.getPhone();
		this.email = entry.getEmail();
	}	
	

	public int getAge(MedicalRecord mR){
		String birthdate = mR.getBirthdate();
		String birthyear = birthdate.substring(birthdate.length() - 4);
		LocalDate currentdate = LocalDate.now();
		int currentYear = currentdate.getYear();		
		return currentYear - Integer.parseInt(birthyear);
	}
	


}
