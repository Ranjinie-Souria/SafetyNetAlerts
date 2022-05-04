package com.safetynet.alerts.repository;

import java.util.List;
import com.safetynet.alerts.model.Person;

public interface IPersonRepository {

	/**
	 * @param name - the name of the Person to find
	 * @return the Person
	 */
	public Person findByName(String name);	

	/**
	 * @return all the persons
	 */
	public List<Person> findAll();

	/**
	 * @param name - the name of the person to delete
	 */
	public void deleteByName(String name);

	/**
	 * @param person - the person to save
	 */
	public void save(Person person);


	

}
