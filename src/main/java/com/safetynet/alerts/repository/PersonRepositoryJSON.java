package com.safetynet.alerts.repository;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.safetynet.alerts.model.Person;

@Repository
public class PersonRepositoryJSON implements IPersonRepository{

	JSONExtracter jsonFile = new JSONExtracter();
	JsonNode jsonPersons;
	
	public PersonRepositoryJSON() throws IOException {
		jsonPersons = jsonFile.getPersons();
	}

	public JsonNode getPersons(){
		return jsonPersons;
	}

	public Optional<Person> findById(Long id) {
		return null;
	}

	public Iterable<Person> findAll() {
		return null;
	}

	public void deleteById(Long id) {
	}

	public Person save(Person person) {
		return null;
	}
    
    
}
