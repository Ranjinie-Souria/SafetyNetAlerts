package com.safetynet.alerts.model;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Component
@RequiredArgsConstructor
public class Home {
	
	private String address;
	private List<PersonAndMedicalInfo> persons;
	
	public Home(String address, List<PersonAndMedicalInfo> persons) {
		this.address = address;
		this.persons = persons;
	}
	

}
