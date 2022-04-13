package com.safetynet.alerts.repository;

import java.util.List;
import com.safetynet.alerts.model.Firestation;

public interface IFirestationRepository {
	
	public List<Firestation> getFirestations();
	
	public Firestation findByStation(String station);	

	public List<Firestation> findAll();

	public void deleteByStation(String station);

	public void save(Firestation firestation);

}
