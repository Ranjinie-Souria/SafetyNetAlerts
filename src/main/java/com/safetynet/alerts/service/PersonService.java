package com.safetynet.alerts.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.ChildInfo;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonAndMedicalInfo;
import com.safetynet.alerts.repository.IFirestationRepository;
import com.safetynet.alerts.repository.IMedicalRecordRepository;
import com.safetynet.alerts.repository.IPersonRepository;

@Service
public class PersonService {
	
	private static final Logger logger = LogManager.getLogger("PersonService");
	
	@Autowired
	private IPersonRepository personRepository;
	
	@Autowired
	private IMedicalRecordRepository medicalRepository;
	
	@Autowired
	private IFirestationRepository firestationRepository;
	
	public Person getPerson(String name) {
		name = name.toLowerCase();
		logger.info("Getting the person with keyname : "+name);
		return personRepository.findByName(name);
	}
	
	public List<Person> getPersons() {
		logger.info("Getting all persons");
		return personRepository.findAll();
	}
	
	public void deletePerson(String name) {
		name = name.toLowerCase();
		logger.info("Deleting the person with keyname : "+name);
		personRepository.deleteByName(name);
	}
	
	public void savePerson(Person person) {
		logger.info("Saving the person : "+person);
		personRepository.save(person);
	}


	public List<ChildInfo> getAllChildFromAddress(String address) throws IOException {
		logger.info("Children's address : "+address);
		LocalDate currentdate = LocalDate.now();
		int currentYear = currentdate.getYear();
		List<ChildInfo> children = new ArrayList<ChildInfo>();
		
		for (MedicalRecord child : medicalRepository.findAll()) {
			
			String birthdate = child.getBirthdate();
			String birthyear = birthdate.substring(birthdate.length() - 4);
			int age = currentYear - Integer.parseInt(birthyear);
			
			if(age < 18) {
				String childAddress = child.getPerson().getAddress();
				if(childAddress.equals(address)) {
					List<String> families = new ArrayList<String>();
					for (Person person : personRepository.findAll()) {
						if(person.getAddress().equals(address)&&
								person.getLastName().equals(child.getPerson().getLastName())) {
							if(!person.getFirstName().equals(child.getPerson().getFirstName())){
								families.add(person.getFirstName()+" "+person.getLastName());
							}
						}
					}
					children.add(new ChildInfo(child.getPerson(),child,families));
					
				}
			}
	    }
		logger.info("Children Information list : "+children);
		return children;
	}
	
	/**
	 * @param city - the city of the emails to find
	 * @return the Persons emails
	 */
	public List<String> findEmailsByCity(String city) {
		List<String> emails = new ArrayList<String>();
		logger.info("Finding emails for the city : "+city);
		for (Person entry : personRepository.findAll()) {
			String personCity = entry.getCity();
			if(personCity.equalsIgnoreCase(city)) {
				emails.add(entry.getEmail());
			}
	    }
		logger.info("Emails list : "+emails);
		return emails;
	}


	/**
	 * @param firstName - the first Name of the Persons to find
	 * @param lastName - the last Name of the Persons to find
	 * @return the Persons info
	 * @throws IOException - the exception if not found
	 */
	public List<PersonAndMedicalInfo> findPersonsByNames(String firstName, String lastName) throws IOException {
		List<PersonAndMedicalInfo> persons = new ArrayList<PersonAndMedicalInfo>();
		
		firstName = firstName.toLowerCase();
		lastName = lastName.toLowerCase();
		MedicalRecord mR = medicalRepository.findByName(firstName+"."+lastName);
		for (Person person : personRepository.findAll()) {

			firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
			lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
			
			if(person.getFirstName().equals(firstName)) {
				if(person.getLastName().equals(lastName)) {
					persons.add(new PersonAndMedicalInfo(person, mR));
				}
			}
			
		}
		logger.info("Persons with name "+firstName+" "+lastName+": "+persons);
		return persons;
	}


	public List<PersonAndMedicalInfo> findByStation(int station) throws IOException {
		List<PersonAndMedicalInfo> personsByStation = new ArrayList<PersonAndMedicalInfo>();
		
		for(Firestation fr : firestationRepository.findByStation(station)) {
			String firestationAddress = fr.getAddress();
			
			for (Person entry : personRepository.findAll()) {
				System.out.println(entry.toString());
				String personAddress = entry.getAddress();
				
				if(personAddress.equalsIgnoreCase(firestationAddress)) {
					String keyName = entry.getFirstName()+"."+entry.getLastName();
					MedicalRecord mR = medicalRepository.findByName(keyName.toLowerCase());
					personsByStation.add(new PersonAndMedicalInfo(entry,mR));
				}
		    }
		}

		return personsByStation;

	}
	
	
	public List<Person> findByAddress(String address) {
		List<Person> persons = new ArrayList<Person>();
		for (Person person : personRepository.findAll()) {
			if(person.getAddress().equals(address)) {
				persons.add(person);
			}
		}
		return persons;
	}

}
