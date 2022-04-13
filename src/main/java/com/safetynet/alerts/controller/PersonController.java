package com.safetynet.alerts.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.service.PersonService;


@RestController
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	/**
	 * Create - Add a new person
	 * @param person An object person
	 * @return The person object saved
	 */
	@PostMapping("/person")
	public Person createPerson(@RequestBody Person person) {
		personService.savePerson(person);
		return person;
	}
	
	
	/**
	 * Read - Get one person 
	 * @param id The id of the person
	 * @return A person object filled
	 */
	@GetMapping("/person/{id}")
	public Person getEmployee(@PathVariable("id") final int id) {
		Person person = personService.getPerson(id);
		if(!person.equals(null)) {
			return person;
		} else {
			return null;
		}
	}
	
	/**
	 * Read - Get all persons
	 * @return - An Iterable object of person filled
	 */
	@GetMapping("/persons")
	public List<Person> getPersons() {
		return personService.getPersons();
	}
	
	/**
	 * Update - Update an existing person
	 * @param id - The id of the person to update
	 * @param person - The person object updated
	 * @return
	 */
	@PutMapping("/person/{id}")
	public Person updatePerson(@PathVariable("id") final int id, @RequestBody Person person) {
		Person per = personService.getPerson(id);
		if(!per.equals(null)) {
			Person currentPerson = per;
			
			String firstName = person.getFirstName();
			if(firstName != null) {
				currentPerson.setFirstName(firstName);
			}
			String lastName = person.getLastName();
			if(lastName != null) {
				currentPerson.setLastName(lastName);;
			}
			String mail = person.getEmail();
			if(mail != null) {
				currentPerson.setEmail(mail);
			}
			String city = person.getCity();
			if(city != null) {
				currentPerson.setCity(city);
			}
			String address = person.getAddress();
			if(address != null) {
				currentPerson.setAddress(address);
			}
			String phone = person.getPhone();
			if(phone != null) {
				currentPerson.setPhone(phone);
			}
			String zip = person.getZip();
			if(zip != null) {
				currentPerson.setZip(zip);
			}
			personService.savePerson(currentPerson);
			return currentPerson;
		} else {
			return null;
		}
	}
	
	
	/**
	 * Delete - Delete a person
	 * @param id - The id of the person to delete
	 */
	@DeleteMapping("/person/{id}")
	public void deletePerson(@PathVariable("id") final int id) {
		personService.deletePerson(id);
	}

}
