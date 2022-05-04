package com.safetynet.alerts.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Person;

@Repository
public class PersonRepositoryJSON implements IPersonRepository{

	JSONExtracter jsonFile = new JSONExtracter();
	HashMap<String, Person> jsonPersons;
	
	public PersonRepositoryJSON() throws IOException {
		jsonPersons = jsonFile.getPersons();
	}

	public Person findByName(String keyName) {
		return jsonPersons.get(keyName);
	}

	public List<Person> findAll() {
		List<Person> persons = new ArrayList<Person>();
		for(Entry<String, Person> entry : jsonPersons.entrySet()) {
			persons.add(entry.getValue());			
		}
		return persons;
	}

	public void deleteByName(String keyName) {
		jsonPersons.remove(keyName);
		
	}

	public void save(Person person) {
		String keyName = person.getFirstName()+"."+person.getLastName();
		jsonPersons.put(keyName,person);
	}
	
    
    
}
