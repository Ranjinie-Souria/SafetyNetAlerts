package com.safetynet.alerts.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.stereotype.Repository;
import com.safetynet.alerts.model.Firestation;

@Repository
public class FirestationRepositoryJSON implements IFirestationRepository {
	
	JSONExtracter jsonFile = new JSONExtracter();
	HashMap<Firestation, Integer> jsonFirestations;

	public FirestationRepositoryJSON() throws IOException {
		jsonFirestations = jsonFile.getFirestations();		
	}
	@Override
	public List<Firestation> findByStation(int station) {
		List<Firestation> fs = new ArrayList<Firestation>();
		for(Entry<Firestation, Integer> entry : jsonFirestations.entrySet()) {
			if(entry.getValue().equals(station)) {
				fs.add(entry.getKey());
			}
		}
		return fs;
	}

	@Override
	public List<Firestation> findAll() {
		List<Firestation> firestations = new ArrayList<Firestation>();
		for(Entry<Firestation, Integer> entry : jsonFirestations.entrySet()) {
			firestations.add(entry.getKey());			
		}
		return firestations;
	}

	@Override
	public void deleteByStation(String station) {
		
		// avoids a ConcurrentModificationException
		Map<Firestation, Integer> map = jsonFirestations;
		Iterator<Map.Entry<Firestation, Integer>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
		    Map.Entry<Firestation, Integer> entry = entries.next();
		    if(entry.getKey().getAddress().equals(station)) {
		    	entries.remove();
			}
		}
		
		/*for(Entry<Firestation, Integer> entry : jsonFirestations.entrySet()) {
			if(entry.getKey().getAddress().equals(station)) {
				jsonFirestations.remove(entry.getKey());
			}
		}*/
		
	}

	@Override
	public void save(Firestation firestation) {
		jsonFirestations.put(firestation,firestation.getStation());
	}

	@Override
	public Firestation findByStationAddress(String address) {
		for(Entry<Firestation, Integer> entry : jsonFirestations.entrySet()) {
			if(entry.getKey().getAddress().equals(address)) {
				return entry.getKey();
			}
		}
		return null;
	}
	

	


}
