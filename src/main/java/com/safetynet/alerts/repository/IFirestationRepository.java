package com.safetynet.alerts.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.FirestationPersonsCovered;
import com.safetynet.alerts.model.Home;
import com.safetynet.alerts.model.MedicalRecord;

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
	public HashMap<Integer, Firestation> findAll();
	
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

	/**
	 * Shows all the persons covered by a firestation
	 * @param station - the number of the firestation
	 * @return all the persons covered
	 * @throws IOException - exception not found
	 */
	public FirestationPersonsCovered getPersonsForFirestation(int station) throws IOException;

	/**
	 * Shows all the phone numbers covered by a firestation
	 * @param station - the number of the firestation
	 * @return all the phone numbers of the persons covered
	 * @throws IOException - exception not found
	 */
	public List<String> getPhoneForFirestation(int station) throws IOException;

	/**
	 * Shows all the persons medical records covered by a firestation by their address
	 * @param address - the address of the firestation
	 * @return all the persons covered
	 * @throws IOException - exception not found
	 */
	public HashMap<String, MedicalRecord> getPersonsForFirestationAddress(String address) throws IOException;

	/**
	 * Shows all the homes covered by each of the firestation
	 * @param stations - numbers of the firestations
	 * @return all the homes covered
	 * @throws IOException - exception not found
	 */
	public List<Home> getHomesForStations(List<Integer> stations) throws IOException;

}
