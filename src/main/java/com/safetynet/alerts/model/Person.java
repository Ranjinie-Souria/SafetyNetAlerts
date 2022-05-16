package com.safetynet.alerts.model;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Component
@RequiredArgsConstructor
public class Person {

	
	
	public Person(String firstName, String lastName, String address, String city, String zip, String phone, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}

	protected String firstName;

	protected String lastName;

	protected String address;

	protected String city;

	protected String zip;

	protected String phone;

	protected String email;
	
}
