package com.safetynet.alerts.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.safetynet.alerts.model.ChildInfo;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonAndMedicalInfo;

public interface IPersonRepository {

	/**
	 * @param name - the name of the Person to find
	 * @return the Person
	 */
	public Person findByName(String name);	

	/**
	 * @return all the persons with the names as keys
	 */
	public HashMap<String, Person> findAll();

	/**
	 * @param name - the name of the person to delete
	 */
	public void deleteByName(String name);

	/**
	 * @param person - the person to save
	 */
	public void save(Person person);

	/**
	 * @param city - the city of the emails to find
	 * @return the Persons emails
	 */
	public List<String> findEmailsByCity(String city);

	/**
	 * @param address - the address of the children to find
	 * @return the children info
	 * @throws IOException - the exception if not found
	 */
	public List<ChildInfo> findChildFromAddress(String address) throws IOException;

	/**
	 * @param firstName - the first Name of the Persons to find
	 * @param lastName - the last Name of the Persons to find
	 * @return the Persons info
	 * @throws IOException - the exception if not found
	 */
	public List<PersonAndMedicalInfo> findPersonsByNames(String firstName, String lastName) throws IOException;
	

}
