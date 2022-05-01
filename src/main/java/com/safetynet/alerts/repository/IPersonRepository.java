package com.safetynet.alerts.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.safetynet.alerts.model.ChildInfo;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonAndMedicalInfo;

public interface IPersonRepository {
	
	public HashMap<String, Person> getPersons();
	
	public Person findByName(String name);	

	public HashMap<String, Person> findAll();

	public void deleteByName(String name);

	public void save(Person person);

	public List<String> findEmailsByCity(String city);

	public List<ChildInfo> findChildFromAddress(String address) throws IOException;

	public List<PersonAndMedicalInfo> findPersonsByNames(String firstName, String lastName) throws IOException;
	

}
