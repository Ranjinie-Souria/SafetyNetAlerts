package com.safetynet.alerts.repository;

import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;
import com.safetynet.alerts.model.Person;

public interface IPersonRepository {
	
	public JsonNode getPersons();
	

	public Optional<Person> findById(Long id);
	

	public Iterable<Person> findAll();
	

	public void deleteById(Long id);

	public Person save(Person person);
	

}
