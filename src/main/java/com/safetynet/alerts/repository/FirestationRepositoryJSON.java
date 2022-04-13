package com.safetynet.alerts.repository;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Firestation;

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
	

}
