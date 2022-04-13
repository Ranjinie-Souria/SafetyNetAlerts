package com.safetynet.alerts.repository;

import java.io.IOException;
import java.util.HashMap;
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
    
    
}
