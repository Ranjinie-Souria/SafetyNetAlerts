package com.safetynet.alerts.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Person;

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
	public HashMap<String, Person> getPersonsForFirestation(int station) throws IOException {
		PersonRepositoryJSON personRepository = new PersonRepositoryJSON();
		return personRepository.findByStation(station);
	}

	@Override
	public List<String> getPhoneForFirestation(int station) throws IOException {
		HashMap<String, Person> persons = getPersonsForFirestation(station);
		List<String> phones = new ArrayList<String>();
		for (Map.Entry<String, Person> entry : persons.entrySet()) {
			phones.add(entry.getValue().getPhone());
	    }
		return phones;
	}



}
