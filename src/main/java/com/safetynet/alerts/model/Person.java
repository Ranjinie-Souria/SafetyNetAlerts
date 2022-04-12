package com.safetynet.alerts.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class Person {

	private String firstName;

	private String lastName;

	private String address;

	private String city;

	private int zip;

	private String phone;

	private String email;
	
}
