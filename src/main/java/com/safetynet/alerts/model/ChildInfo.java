package com.safetynet.alerts.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Component
@RequiredArgsConstructor
public class ChildInfo{
	
	private Person p;
	private String firstName;
	private String lastName;
	private String address;
	private int age;
	private String phone;
	private MedicalRecord mR;
	private List<Person> family;
	
	public ChildInfo(Person p, MedicalRecord mR,List<Person> family) {
		this.p = p;
		this.mR = mR;
		this.family = family;
		
		this.firstName = p.getFirstName();
		this.lastName = p.getLastName();
		this.address = p.getAddress();
		
		String birthdate = mR.getBirthdate();
		String birthyear = birthdate.substring(birthdate.length() - 4);
		LocalDate currentdate = LocalDate.now();
		int currentYear = currentdate.getYear();		
		this.age = currentYear - Integer.parseInt(birthyear);
	}
	
	

}
