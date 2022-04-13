package com.safetynet.alerts.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.IPersonRepository;

public class PersonService {
	
	@Autowired
	private IPersonRepository personRepository;
	
	public Optional<Person> getPerson(final Long id) {
		return personRepository.findById(id);
	}
	
	public Iterable<Person> getPersons() {
		return personRepository.findAll();
	}
	
	public void deletePerson(final Long id) {
		personRepository.deleteById(id);
	}
	
	public Person savePerson(Person person) {
		Person savedPerson = personRepository.save(person);
		return savedPerson;
	}

}
