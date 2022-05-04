package com.safetynet.alerts.repository;

import java.util.List;

import com.safetynet.alerts.model.Firestation;

public interface IFirestationRepository {


	/**
	 * @param station - the number of the station
	 * @return the station found
	 */
	public Firestation findByStation(int station);	

	/**
	 * @return All the firestations with 
	 * their number as a key
	 */
	public List<Firestation> findAll();
	
	/**
	 * Deletes a firestation
	 * @param station - id of the firestation 
	 * to delete
	 */
	public void deleteByStation(int station);

	/**
	 * Adds a firestation
	 * @param firestation - the firestation to add
	 * 
	 */
	public void save(Firestation firestation);



}
