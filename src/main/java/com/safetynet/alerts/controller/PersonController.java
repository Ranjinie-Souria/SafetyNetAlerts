package com.safetynet.alerts.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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
	
	@Autowired
	private PersonService personService;
	
	/**
	 * Create - Add a new person
	 * @param person An object person
	 * @return The person object saved
	 */
	@PostMapping("/person")
	//@RequestMapping(value = "/person", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Person createPerson(@RequestBody Person person) {
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
		Person person = personService.getPerson(name);
		if(person != null) {
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
	public HashMap<String, Person> getPersons() {
		return personService.getPersons();
	}
	
	/**
	 * Update - Update an existing person
	 * @param name - The name of the person to update
	 * @param person - The person object updated
	 * @return
	 */
	@PutMapping("/person/{name}")
	public Person updatePerson(@PathVariable("name") final String name, @RequestBody Person person) {
		Person per = personService.getPerson(name);
		if(!per.equals(null)) {
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
			return null;
		}
	}
	
	
	/**
	 * Delete - Delete a person
	 * @param name - The name of the person to delete
	 */
	@DeleteMapping("/person/{name}")
	public void deletePerson(@PathVariable("name") final String name) {
		personService.deletePerson(name);
	}
	
	/**
	 * Read - Get all persons email living in the city
	 * @param city - The city
	 * @return - list of email
	 */
	@GetMapping(path = "/communityEmail")
	public List<String> getPersonsForFirestation(@RequestParam(name = "city") String city){
		return personService.getPersonsEmailByCity(city);
	}
	
	
	/**
	 * Read - Get all children living in the 
	 * address and the members of their family
	 * @param address - The address
	 * @return - list of children
	 * @throws IOException 
	 */
	@GetMapping(path = "/childAlert")
	public List<ChildInfo> getAllChildFromAddress(@RequestParam(name = "address") String address) throws IOException{
		return personService.getAllChildFromAddress(address);
	}
	
	/**
	 * Read - Get all persons with the name
	 * @param firstName - First name
	 * @param lastName - Last  name
	 * @return - list of persons
	 * @throws IOException 
	 */
	@GetMapping(path = "/personInfo")
	public List<PersonAndMedicalInfo> getPersonsInfo(@RequestParam(name = "firstName") String firstName,@RequestParam(name = "lastName") String lastName) throws IOException{
		return personService.getPersonsByNames(firstName,lastName);
	}
	
	
	
	
	
	

}
