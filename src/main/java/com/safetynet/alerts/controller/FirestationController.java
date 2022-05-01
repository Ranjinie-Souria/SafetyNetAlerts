package com.safetynet.alerts.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.FirestationPersonsCovered;
import com.safetynet.alerts.model.Home;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.FirestationService;

@RestController
public class FirestationController {
	
	@Autowired
	private FirestationService firestationService;
	
	/**
	 * Create - Add a new firestation
	 * @param firestation An object firestation
	 * @return The firestation object saved
	 */
	@PostMapping("/firestation")
	public Firestation createFirestation(@RequestBody Firestation firestation) {
		firestationService.saveFirestation(firestation);
		return firestation;
	}
	
	
	/**
	 * Read - Get one firestation 
	 * @param station - The id of the firestation
	 * @return A firestation object filled
	 */
	@GetMapping("/firestation/{station}")
	public Firestation getFirestation(@PathVariable("station") int station) {
		Firestation firestation = firestationService.getFirestation(station);
		if(!firestation.equals(null)) {
			return firestation;
		} else {
			return null;
		}
	}
	
	/**
	 * Read - Get all firestations
	 * @return - An Iterable object of firestation filled
	 */
	@GetMapping("/firestations")
	public HashMap<Integer, Firestation> getFirestations() {
		return firestationService.getFirestations();
	}
	
	/**
	 * Update - Update an existing firestation
	 * @param station - The id of the firestation to update
	 * @param firestation - The firestation object updated
	 * @return - the updated firestion
	 */
	@PutMapping("/firestation/{station}")
	public Firestation updateFirestation(@PathVariable("station") final int station, @RequestBody Firestation firestation) {
		Firestation fire = firestationService.getFirestation(station);
		if(!fire.equals(null)) {
			Firestation currentFirestation = fire;
			
			String address = firestation.getAddress();
			if(address != null) {
				currentFirestation.setAddress(address);
			}
			
			firestationService.saveFirestation(currentFirestation);
			return currentFirestation;
		} else {
			return null;
		}
	}
	
	
	/**
	 * Delete - Delete a firestation
	 * @param station - The id of the firestation to delete
	 */
	@DeleteMapping("/firestation/{station}")
	public void deleteFirestation(@PathVariable("station") final int station) {
		firestationService.deleteFirestation(station);
	}
	
	/**
	 * Read - Get all persons living next to the firestation
	 * @param stationNumber - The stationNumber of the firestation
	 * @return - An Iterable object of persons filled
	 * @throws IOException - exception not found
	 */
	@GetMapping(path = "/firestation")
	public FirestationPersonsCovered getPersonsForFirestation(@RequestParam(name = "stationNumber") int stationNumber) throws IOException {
		return firestationService.getPersonsForFirestation(stationNumber);
	}
	
	/**
	 * Read - Get all phone numbers of people living next to the firestation
	 * @param firestation - The stationNumber of the firestation
	 * @return - A list of phone numbers
	 * @throws IOException - exception not found
	 */
	@GetMapping(path = "/phoneAlert")
	public List<String> getPhonesForFirestation(@RequestParam(name = "firestation") int firestation) throws IOException {
		return firestationService.getPhoneForFirestation(firestation);
	}
	
	/**
	 * Read - Get all persons living next to the firestation with address
	 * @param address - The address
	 * @return - A list of persons
	 * @throws IOException - exception not found 
	 */
	@GetMapping(path = "/fire")
	public HashMap<String, MedicalRecord> getPersonsForFirestationAddress(@RequestParam(name = "address") String address) throws IOException {
		return firestationService.getPersonsForFirestationAddress(address);
	}
	
	
	
	/**
	 * Read - Get all homes living next to each of the stations
	 * @param stations - A list of stations numbers
	 * @return - A list of homes
	 * @throws IOException - exception not found 
	 */
	@GetMapping(path = "/flood/stations")
	public List<Home> getFlood(@RequestParam(name = "stations") List<Integer> stations) throws IOException {
		return firestationService.getHomesForStations(stations);
	}

	
	
	


}
