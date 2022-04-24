package com.safetynet.alerts.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.IPersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private IPersonRepository personRepository;
	
	public Person getPerson(String name) {
		name = name.toLowerCase();
		return personRepository.findByName(name);
	}
	
	public HashMap<String, Person> getPersons() {
		return personRepository.findAll();
	}
	
	public void deletePerson(String name) {
		name = name.toLowerCase();
		personRepository.deleteByName(name);
	}
	
	public void savePerson(Person person) {
		personRepository.save(person);
	}
	
	public List<String> getPersonsEmailByCity(String city) {
		return personRepository.findEmailsByCity(city);
	}

}
