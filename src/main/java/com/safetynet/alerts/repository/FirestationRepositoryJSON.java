package com.safetynet.alerts.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.FirestationPersonsCovered;
import com.safetynet.alerts.model.Home;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonAndMedicalInfo;
import com.safetynet.alerts.model.PersonCoveredByFirestation;

@Repository
public class FirestationRepositoryJSON implements IFirestationRepository {
	
	JSONExtracter jsonFile = new JSONExtracter();
	HashMap<Integer,Firestation> jsonFirestations;

	public FirestationRepositoryJSON() throws IOException {
		jsonFirestations = jsonFile.getFirestations();		
	}

	@Override
	public HashMap<Integer, Firestation> getFirestations() {
		return jsonFirestations;
	}

	@Override
	public Firestation findByStation(int station) {
		return jsonFirestations.get(station);
	}

	@Override
	public HashMap<Integer, Firestation> findAll() {
		return jsonFirestations;
	}

	@Override
	public void deleteByStation(int station) {
		jsonFirestations.remove(station);
	}

	@Override
	public void save(Firestation firestation) {
		jsonFirestations.put(firestation.getStation(),firestation);
	}
	
	@Override
	public FirestationPersonsCovered getPersonsForFirestation(int station) throws IOException {
		PersonRepositoryJSON personRepository = new PersonRepositoryJSON();
		return new FirestationPersonsCovered(personRepository.findByStation(station));
	}

	@Override
	public List<String> getPhoneForFirestation(int station) throws IOException {
		FirestationPersonsCovered persons = getPersonsForFirestation(station);
		List<String> phones = new ArrayList<String>();
		for (Entry<String, PersonCoveredByFirestation> entry : persons.getPersonsCovered().entrySet()) {
			phones.add(entry.getValue().getPhone());
	    }
		return phones;
	}

	@Override
	public HashMap<String, MedicalRecord> getPersonsForFirestationAddress(String address) throws IOException {
		HashMap<String, MedicalRecord> personsForAddress = new HashMap<String, MedicalRecord>();

		PersonRepositoryJSON personRepository = new PersonRepositoryJSON();
		MedicalRecordRepositoryJSON medicalRecordRepository = new MedicalRecordRepositoryJSON();
		
		for(Entry<String, Person> entry : personRepository.findByAddress(address).entrySet()) {
			
			if(entry.getValue().equals(medicalRecordRepository.findByName(entry.getKey()).getPerson())) {
				personsForAddress.put(entry.getKey(),medicalRecordRepository.findByName(entry.getKey()));
			}
		}
		
		return personsForAddress;
	}

	@Override
	public List<Home> getHomesForStations(List<Integer> stations) throws IOException {
		List<Home> homes = new ArrayList<Home>();
		PersonRepositoryJSON pRJSON = new PersonRepositoryJSON();
		
		for(int station : stations) {
			List<PersonAndMedicalInfo> persons = new ArrayList<PersonAndMedicalInfo>();
			String address = this.findByStation(station).getAddress();
			
			Map<String, Person> personsByAddress = pRJSON.findByAddress(address);
			
			for (Entry<String, Person> person : personsByAddress.entrySet()) {
				
				List<PersonAndMedicalInfo> personByNames = pRJSON.findPersonsByNames(person.getValue().getFirstName(), person.getValue().getLastName());
				persons.add(personByNames.get(0));

			}
			
			homes.add(new Home(address, persons));
			
		}
		
		
		
		return homes;
	}



}
