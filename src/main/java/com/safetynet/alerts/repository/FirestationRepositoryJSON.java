package com.safetynet.alerts.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
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
	public Firestation findByStation(int station) {
		return jsonFirestations.get(station);
	}

	@Override
	public List<Firestation> findAll() {
		List<Firestation> firestations = new ArrayList<Firestation>();
		for(Entry<Integer, Firestation> entry : jsonFirestations.entrySet()) {
			firestations.add(entry.getValue());			
		}
		return firestations;
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
