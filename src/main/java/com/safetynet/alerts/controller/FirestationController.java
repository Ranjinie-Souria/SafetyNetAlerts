package com.safetynet.alerts.controller;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Person;
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
	 * @param id The id of the firestation
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
	 * @param id - The id of the firestation to update
	 * @param firestation - The firestation object updated
	 * @return
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
	 * @param id - The id of the firestation to delete
	 */
	@DeleteMapping("/firestation/{station}")
	public void deleteFirestation(@PathVariable("station") final int station) {
		firestationService.deleteFirestation(station);
	}
	
	/**
	 * Read - Get all persons living next to the firestation
	 * @return - An Iterable object of persons filled
	 * @throws IOException 
	 */
	@GetMapping(path = "/firestation")
	public HashMap<String, Person> getPersonsForFirestation(@RequestParam(name = "stationNumber") int station) throws IOException {
		return firestationService.getPersonsForFirestation(station);
	}
	


}
