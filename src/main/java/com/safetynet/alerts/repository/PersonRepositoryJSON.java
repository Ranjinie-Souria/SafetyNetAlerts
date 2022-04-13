package com.safetynet.alerts.repository;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.safetynet.alerts.model.Person;

@Repository
public class PersonRepositoryJSON implements IPersonRepository{

	JSONExtracter jsonFile = new JSONExtracter();
	List<Person> jsonPersons;
	
	public PersonRepositoryJSON() throws IOException {
		jsonPersons = jsonFile.getPersons();
	}

	public List<Person> getPersons(){
		return jsonPersons;
	}

	public Person findById(int id) {
		return jsonPersons.get(id);
	}

	public List<Person> findAll() {
		return jsonPersons;
	}

	public void deleteById(int id) {
		jsonPersons.remove(id);
		
	}

	public void save(Person person) {
		jsonPersons.add(person);
	}
    
    
}
