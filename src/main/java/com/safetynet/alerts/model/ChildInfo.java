package com.safetynet.alerts.model;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@Component
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ChildInfo extends Person{
	
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
		this.city = person.getCity();
		this.zip = person.getZip();
		this.email = person.getEmail();
		
		int childAge = new PersonAndMedicalInfo(medicalRecord).getAge();
		this.age = childAge;
	}
}
