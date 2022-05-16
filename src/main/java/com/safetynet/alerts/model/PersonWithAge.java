package com.safetynet.alerts.model;

import java.time.LocalDate;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@Component
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PersonWithAge extends Person{
	
	private int age;
	
	public PersonWithAge(MedicalRecord mR) {
		this.setAge(mR);
		Person entry = mR.getPerson();
		this.firstName = entry.getFirstName();
		this.lastName = entry.getLastName();
		this.address = entry.getAddress();
		this.city = entry.getCity();
		this.zip = entry.getZip();
		this.phone = entry.getPhone();
		this.email = entry.getEmail();
	}	
	
	public PersonWithAge(PersonWithAge entry) {
		this.firstName = entry.getFirstName();
		this.lastName = entry.getLastName();
		this.address = entry.getAddress();
		this.city = entry.getCity();
		this.zip = entry.getZip();
		this.phone = entry.getPhone();
		this.email = entry.getEmail();
		this.age = entry.getAge();
	}
	
	public PersonWithAge(Person entry, MedicalRecord mR) {
		this.firstName = entry.getFirstName();
		this.lastName = entry.getLastName();
		this.address = entry.getAddress();
		this.city = entry.getCity();
		this.zip = entry.getZip();
		this.phone = entry.getPhone();
		this.email = entry.getEmail();
		this.setAge(mR);
	}

	public void setAge(MedicalRecord mR){
		String birthdate = mR.getBirthdate();
		String birthyear = birthdate.substring(birthdate.length() - 4);
		LocalDate currentdate = LocalDate.now();
		int currentYear = currentdate.getYear();		
		this.age = currentYear - Integer.parseInt(birthyear);
	}

}
