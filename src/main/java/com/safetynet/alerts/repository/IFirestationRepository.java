package com.safetynet.alerts.repository;

import java.util.HashMap;
import com.safetynet.alerts.model.Firestation;

public interface IFirestationRepository {
	
	public HashMap<Integer, Firestation> getFirestations();
	
	public Firestation findByStation(int station);	

	public HashMap<Integer, Firestation> findAll();

	public void deleteByStation(int station);

	public void save(Firestation firestation);

}
