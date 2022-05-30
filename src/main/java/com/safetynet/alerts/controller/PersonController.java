package com.safetynet.alerts.controller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.ChildInfo;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonAndMedicalInfo;
import com.safetynet.alerts.service.PersonService;


@RestController
public class PersonController {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	@Autowired
	private PersonService personService;
	
	/**
	 * Create - Add a new person
	 * @param person An object person
	 * @return The person object saved
	 */
	@PostMapping("/person")
	public Person createPerson(@RequestBody Person person) {
		logger.info("Saving person : "+person);
		personService.savePerson(person);
		return person;
	}
	
	
	/**
	 * Read - Get one person 
	 * @param name The name of the person
	 * @return A person object filled
	 */
	@GetMapping("/person/{name}")
	public Person getPerson(@PathVariable("name") String name) {
		logger.info("Getting person with name : "+name);
		Person person = personService.getPerson(name);
		if(person != null) {
			logger.info("Person found");
			return person;
		} else {
			logger.error("Person not found");
			return null;
		}
	}
	
	/**
	 * Read - Get all persons
	 * @return - An Iterable object of person filled
	 */
	@GetMapping("/persons")
	public List<Person> getPersons() {
		logger.info("Getting all persons");
		return personService.getPersons();
	}
	
	/**
	 * Update - Update an existing person
	 * @param name - The name of the person to update
	 * @param person - The person object updated
	 * @return - the updated person
	 */
	@PutMapping("/person/{name}")
	public Person updatePerson(@PathVariable("name") final String name, @RequestBody Person person) {
		logger.info("Updating person : "+name);
		Person per = personService.getPerson(name);
		if(!(per == null)) {
			Person currentPerson = per;
			
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
			logger.error("Person could not be updated");
			return null;
		}
	}
	
	
	/**
	 * Delete - Delete a person
	 * @param name - The name of the person to delete
	 */
	@DeleteMapping("/person/{name}")
	public void deletePerson(@PathVariable("name") final String name) {
		logger.info("Deleting Person : "+name);
		personService.deletePerson(name);
	}
	
	/**
	 * Read - Get all persons email living in the city
	 * @param city - The city
	 * @return - list of email
	 */
	@GetMapping(path = "/communityEmail")
	public List<String> getPersonsForFirestation(@RequestParam(name = "city") String city){
		logger.info("Getting all emails in city : "+city);
		return personService.findEmailsByCity(city);
	}
	
	
	/**
	 * Read - Get all children living in the 
	 * address and the members of their family
	 * @param address - The address
	 * @return - list of children
	 * @throws IOException - exception not found 
	 */
	@GetMapping(path = "/childAlert")
	public List<ChildInfo> getAllChildFromAddress(@RequestParam(name = "address") String address) throws IOException{
		logger.info("Getting all children in address : "+address);
		return personService.getAllChildFromAddress(address);
	}
	
	/**
	 * Read - Get all persons with the name
	 * @param firstName - First name
	 * @param lastName - Last  name
	 * @return - list of persons
	 * @throws IOException - exception not found 
	 */
	@GetMapping(path = "/personInfo")
	public List<PersonAndMedicalInfo> getPersonsInfo(@RequestParam(name = "firstName") String firstName,@RequestParam(name = "lastName") String lastName) throws IOException{
		logger.info("Getting all persons with name : "+firstName+" "+lastName);
		return personService.findPersonsByNames(firstName, lastName);
	}
	
	
	
	
	
	

}
