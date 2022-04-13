package com.safetynet.alerts.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.repository.IFirestationRepository;

@Service
public class FirestationService {
	
	@Autowired
	private IFirestationRepository firestationRepository;
	
	public Firestation getFirestation(int station) {
		return firestationRepository.findByStation(station);
	}
	
	public HashMap<Integer, Firestation> getFirestations() {
		return firestationRepository.findAll();
	}
	
	public void deleteFirestation(int station) {
		firestationRepository.deleteByStation(station);
	}
	
	public void saveFirestation(Firestation firestation) {
		firestationRepository.save(firestation);
	}

}
