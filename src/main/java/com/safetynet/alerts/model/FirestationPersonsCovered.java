package com.safetynet.alerts.model;

import java.util.HashMap;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Component
@RequiredArgsConstructor
public class FirestationPersonsCovered {
	
	private int countAdults;
	private int countChildren;
	private HashMap<String, PersonCoveredByFirestation> personsCovered;
	
	public FirestationPersonsCovered(HashMap<String, PersonCoveredByFirestation> personsCovered) {
		this.personsCovered = personsCovered;
		this.countAdults = 0;
		this.countChildren = 0;
		
		for(Entry<String, PersonCoveredByFirestation> personCovered : personsCovered.entrySet()) {
			if(personCovered.getValue().getAge()>=18) {
				countAdults += 1;
			}
			else {
				countChildren += 1;
			}
		}
		
	}
	
	

}
