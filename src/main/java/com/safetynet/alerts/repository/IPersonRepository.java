package com.safetynet.alerts.repository;

import java.util.List;
import com.safetynet.alerts.model.Person;

public interface IPersonRepository {
	
	public List<Person> getPersons();
	

	public Person findById(int id);
	

	public List<Person> findAll();
	

	public void deleteById(int id);

	public void save(Person person);
	

}
