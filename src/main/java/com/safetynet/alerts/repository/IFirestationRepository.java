package com.safetynet.alerts.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.FirestationPersonsCovered;
import com.safetynet.alerts.model.MedicalRecord;

public interface IFirestationRepository {
	
	public HashMap<Integer, Firestation> getFirestations();
	
	public Firestation findByStation(int station);	

	public HashMap<Integer, Firestation> findAll();

	public void deleteByStation(int station);

	public void save(Firestation firestation);

	public FirestationPersonsCovered getPersonsForFirestation(int station) throws IOException;

	public List<String> getPhoneForFirestation(int station) throws IOException;

	public HashMap<String, MedicalRecord> getPersonsForFirestationAddress(String address) throws IOException;

}
