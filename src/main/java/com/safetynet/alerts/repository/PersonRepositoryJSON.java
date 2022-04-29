package com.safetynet.alerts.repository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.ChildInfo;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonCoveredByFirestation;

@Repository
public class PersonRepositoryJSON implements IPersonRepository{

	JSONExtracter jsonFile = new JSONExtracter();
	HashMap<String, Person> jsonPersons;
	
	public PersonRepositoryJSON() throws IOException {
		jsonPersons = jsonFile.getPersons();
	}

	public HashMap<String, Person> getPersons(){
		return jsonPersons;
	}

	public Person findByName(String keyName) {
		return jsonPersons.get(keyName);
	}

	public HashMap<String, Person> findAll() {
		return jsonPersons;
	}

	public void deleteByName(String keyName) {
		jsonPersons.remove(keyName);
		
	}

	public void save(Person person) {
		String keyName = person.getFirstName()+"."+person.getLastName();
		jsonPersons.put(keyName,person);
	}
	
	public HashMap<String, PersonCoveredByFirestation> findByStation(int station) throws IOException {
		
		HashMap<String, PersonCoveredByFirestation> personsByStation = new HashMap<String, PersonCoveredByFirestation>();
		FirestationRepositoryJSON firestationRepository = new FirestationRepositoryJSON();
		String firestationAddress = firestationRepository.findByStation(station).getAddress();
		
		for (Map.Entry<String, Person> entry : jsonPersons.entrySet()) {
			String personAddress = entry.getValue().getAddress();
			if(personAddress.equalsIgnoreCase(firestationAddress)) {
				personsByStation.put(entry.getKey(),new PersonCoveredByFirestation(entry.getValue()));
			}
	    }
		return personsByStation;
	}
	
	public List<String> findEmailsByCity(String city) {
		List<String> emails = new ArrayList<String>();
		for (Map.Entry<String, Person> entry : jsonPersons.entrySet()) {
			String personCity = entry.getValue().getCity();
			if(personCity.equalsIgnoreCase(city)) {
				emails.add(entry.getValue().getEmail());
			}
	    }
		return emails;
	}

	@Override
	public List<ChildInfo> findChildFromAddress(String address) throws IOException {
		MedicalRecordRepositoryJSON medicalRepository = new MedicalRecordRepositoryJSON();
		LocalDate currentdate = LocalDate.now();
		int currentYear = currentdate.getYear();
		List<ChildInfo> children = new ArrayList<ChildInfo>();
		
		for (Entry<String, MedicalRecord> child : medicalRepository.getMedicalRecords().entrySet()) {
			
			String birthdate = child.getValue().getBirthdate();
			String birthyear = birthdate.substring(birthdate.length() - 4);
			int age = currentYear - Integer.parseInt(birthyear);
			
			if(age < 18) {
				String childAddress = child.getValue().getPerson().getAddress();
				if(childAddress.equals(address)) {
					List<String> families = new ArrayList<String>();
					for (Entry<String, Person> person : jsonPersons.entrySet()) {
						if(person.getValue().getAddress().equals(address)&&
								person.getValue().getLastName().equals(child.getValue().getPerson().getLastName())) {
							if(!person.getValue().getFirstName().equals(child.getValue().getPerson().getFirstName())){
								families.add(person.getValue().getFirstName()+" "+person.getValue().getLastName());
							}
						}
					}
					children.add(new ChildInfo(child.getValue().getPerson(),child.getValue(),families));
					
				}
			}
	    }
		return children;
	}

	public Map<String, Person> findByAddress(String address) {
		Map<String, Person> persons = new HashMap<String, Person>();
		for (Entry<String, Person> person : jsonPersons.entrySet()) {
			if(person.getValue().getAddress().equals(address)) {
				persons.put(person.getKey(), person.getValue());
			}
		}
		return persons;
	}
    
    
}
