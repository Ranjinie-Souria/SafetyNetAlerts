package com.safetynet.alerts.repository;

import java.util.HashMap;
import com.safetynet.alerts.model.Person;

public interface IPersonRepository {
	
	public HashMap<String, Person> getPersons();
	
	public Person findByName(String name);	

	public HashMap<String, Person> findAll();

	public void deleteByName(String name);

	public void save(Person person);
	

}
