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
public class ChildInfo extends PersonInfo{
	
	private String firstName;
	private String lastName;
	private String address;
	private int age;
	private String phone;
	private List<String> family;
	
	public ChildInfo(Person person, MedicalRecord medicalRecord,List<String> family) {
		this.family = family;
		
		this.firstName = person.getFirstName();
		this.lastName = person.getLastName();
		this.address = person.getAddress();
		this.phone = person.getPhone();
		
		String birthdate = medicalRecord.getBirthdate();
		String birthyear = birthdate.substring(birthdate.length() - 4);
		LocalDate currentdate = LocalDate.now();
		int currentYear = currentdate.getYear();		
		this.age = currentYear - Integer.parseInt(birthyear);
	}
}
