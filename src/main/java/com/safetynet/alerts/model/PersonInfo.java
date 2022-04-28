package com.safetynet.alerts.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Component
@RequiredArgsConstructor
public class PersonInfo {
	
	private Person p;
	private String firstName;
	private String lastName;
	private String address;
	private int age;
	private MedicalRecord mR;
	private String phone;
	
	public PersonInfo(Person p, MedicalRecord mR) {
		this.p = p;
		this.mR = mR;
		
		this.firstName = p.getFirstName();
		this.lastName = p.getLastName();
		this.address = p.getAddress();
		this.phone = p.getPhone();
		
		String birthdate = mR.getBirthdate();
		String birthyear = birthdate.substring(birthdate.length() - 4);
		LocalDate currentdate = LocalDate.now();
		int currentYear = currentdate.getYear();		
		this.age = currentYear - Integer.parseInt(birthyear);
	}
	


}
