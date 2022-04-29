package com.safetynet.alerts.model;

import java.io.IOException;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

import com.safetynet.alerts.repository.IMedicalRecordRepository;
import com.safetynet.alerts.repository.MedicalRecordRepositoryJSON;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@Component
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PersonCoveredByFirestation extends Person{
	private int age;
	
	public PersonCoveredByFirestation(Person person) throws IOException {
		this.firstName = person.firstName;
		this.lastName = person.lastName;
		this.address = person.address;
		this.city = person.city;
		this.zip = person.zip;
		this.phone = person.phone;
		this.email = person.email;
		
		IMedicalRecordRepository mRP = new MedicalRecordRepositoryJSON();
		MedicalRecord mR = mRP.findByName((person.firstName+"."+person.lastName).toLowerCase());
		LocalDate currentdate = LocalDate.now();
		int currentYear = currentdate.getYear();		
		String birthdate = mR.getBirthdate();
		String birthyear = birthdate.substring(birthdate.length() - 4);
		this.age = currentYear - Integer.parseInt(birthyear);
	}

}
