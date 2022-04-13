package com.safetynet.alerts.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.IPersonRepository;

public class PersonService {
	
	@Autowired
	private IPersonRepository personRepository;
	
	public Person getPerson(final int id) {
		return personRepository.findById(id);
	}
	
	public List<Person> getPersons() {
		return personRepository.findAll();
	}
	
	public void deletePerson(final int id) {
		personRepository.deleteById(id);
	}
	
	public void savePerson(Person person) {
		personRepository.save(person);
	}

}
