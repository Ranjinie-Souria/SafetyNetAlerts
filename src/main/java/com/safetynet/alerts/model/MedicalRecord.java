package com.safetynet.alerts.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Component
@RequiredArgsConstructor
public class MedicalRecord {
	private int age;
	private Person person;
	private String birthdate;
	private List<String> medications;
	private List<String> allergies;
	
	public MedicalRecord(Person person, String birthdate, List<String> medications, List<String> allergies) {
		this.person = person;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
		this.age = this.calculateAge(birthdate);
	}
	
	
	public int calculateAge(String birthdate){
		try {
		    Date birth=new SimpleDateFormat("dd/MM/yyyy").parse(birthdate);  
		     
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTime(birth);
		    calendar.get(Calendar.YEAR);
		    
			LocalDate currentdate = LocalDate.now();
			LocalDate dob = birth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			Period period = Period.between(dob, currentdate);
			return period.getYears();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return age;
	}
	

}
