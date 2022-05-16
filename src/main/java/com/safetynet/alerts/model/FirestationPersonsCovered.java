package com.safetynet.alerts.model;

import java.util.List;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Component
@RequiredArgsConstructor
public class FirestationPersonsCovered {
	
	private int countAdults;
	private int countChildren;
	private List<PersonWithAge> personsCovered;
	
	public FirestationPersonsCovered(List<PersonWithAge> personsCovered) {
		this.personsCovered = personsCovered;
		this.countAdults = 0;
		this.countChildren = 0;
		
		for(PersonWithAge personCovered : personsCovered) {
			if(personCovered.getAge()>=18) {
				countAdults += 1;
			}
			else {
				countChildren += 1;
			}
		}
		
	}
	
	

}
