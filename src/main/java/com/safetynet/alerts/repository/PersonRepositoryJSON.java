package com.safetynet.alerts.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Person;

@Repository
public class PersonRepositoryJSON implements IPersonRepository{

	JSONExtracter jsonFile = new JSONExtracter();
	HashMap<String, Person> jsonPersons;
	
	public PersonRepositoryJSON() throws IOException {
		jsonPersons = jsonFile.getPersons();
	}

	public HashMap<String, Person> getPersons(){
		return jsonPersons;
	}

	public Person findByName(String keyName) {
		return jsonPersons.get(keyName);
	}

	public HashMap<String, Person> findAll() {
		return jsonPersons;
	}

	public void deleteByName(String keyName) {
		jsonPersons.remove(keyName);
		
	}

	public void save(Person person) {
		String keyName = person.getFirstName()+"."+person.getLastName();
		jsonPersons.put(keyName,person);
	}
	
	public HashMap<String, Person> findByStation(int station) throws IOException {
		HashMap<String, Person> personsByStation = new HashMap<String, Person>();
		FirestationRepositoryJSON firestationRepository = new FirestationRepositoryJSON();
		String firestationAddress = firestationRepository.findByStation(station).getAddress();
		for (Map.Entry<String, Person> entry : jsonPersons.entrySet()) {
			String personAddress = entry.getValue().getAddress();
			if(personAddress.equalsIgnoreCase(firestationAddress)) {
				personsByStation.put(entry.getKey(),entry.getValue());
			}
	    }
		return personsByStation;
	}
	
	public List<String> findEmailsByCity(String city) {
		List<String> emails = new ArrayList<String>();
		for (Map.Entry<String, Person> entry : jsonPersons.entrySet()) {
			String personCity = entry.getValue().getCity();
			if(personCity.equalsIgnoreCase(city)) {
				emails.add(entry.getValue().getEmail());
			}
	    }
		return emails;
	}
    
    
}
