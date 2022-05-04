package com.safetynet.alerts.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.FirestationPersonsCovered;
import com.safetynet.alerts.model.Home;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.PersonAndMedicalInfo;
import com.safetynet.alerts.model.PersonCoveredByFirestation;
import com.safetynet.alerts.repository.IFirestationRepository;
import com.safetynet.alerts.repository.IMedicalRecordRepository;

@Service
public class FirestationService {
	
	@Autowired
	private IFirestationRepository firestationRepository;
	@Autowired
	private PersonService personRepository;
	@Autowired
	private IMedicalRecordRepository medicalRecordRepository;
	
	public Firestation getFirestation(String station) {
		return firestationRepository.findByStationAddress(station);
	}
	
	public List<Firestation> getFirestationsByStation(int station) {
		return firestationRepository.findByStation(station);
	}
	
	public List<Firestation> getFirestations() {
		return firestationRepository.findAll();
	}
	
	public void deleteFirestation(String station) {
		firestationRepository.deleteByStation(station);
	}
	
	public void saveFirestation(Firestation firestation) {
		firestationRepository.save(firestation);
	}
	
	/**
	 * Shows all the persons covered by a firestation
	 * @param station - the number of the firestation
	 * @return all the persons covered
	 * @throws IOException - exception not found
	 */
	public FirestationPersonsCovered getPersonsForFirestation(int station) throws IOException {
		return new FirestationPersonsCovered(personRepository.findByStation(station));
	}

	/**
	 * Shows all the phone numbers covered by a firestation
	 * @param station - the number of the firestation
	 * @return all the phone numbers of the persons covered
	 * @throws IOException - exception not found
	 */
	public List<String> getPhoneForFirestation(int station) throws IOException {
		FirestationPersonsCovered persons = getPersonsForFirestation(station);
		List<String> phones = new ArrayList<String>();
		for (PersonCoveredByFirestation entry : persons.getPersonsCovered()) {
			phones.add(entry.getPhone());
	    }
		return phones;
	}

	/**
	 * Shows all the persons medical records covered by a firestation by their address
	 * @param address - the address of the firestation
	 * @return all the persons covered
	 * @throws IOException - exception not found
	 */
	public List<MedicalRecord> getPersonsForFirestationAddress(String address) throws IOException {
		List<MedicalRecord> personsForAddress = new ArrayList<MedicalRecord>();
		
		for(Person entry : personRepository.findByAddress(address)) {
			MedicalRecord personAddress = medicalRecordRepository.findByName(entry.getFirstName().toLowerCase()+"."+entry.getLastName().toLowerCase());
			if(entry.equals(personAddress.getPerson())) {
				personsForAddress.add(personAddress);
			}
		}
		
		return personsForAddress;
	}

	/**
	 * Shows all the homes covered by each of the firestation
	 * @param stations - numbers of the firestations
	 * @return all the homes covered
	 * @throws IOException - exception not found
	 */
	public List<Home> getHomesForStations(List<Integer> stations) throws IOException {
		List<Home> homes = new ArrayList<Home>();		
		for(int station : stations) {
			List<PersonAndMedicalInfo> persons = new ArrayList<PersonAndMedicalInfo>();
			
			for(Firestation fr : firestationRepository.findByStation(station)) {
				String address = fr.getAddress();
				List<Person> personsByAddress = personRepository.findByAddress(address);
				
				for (Person person : personsByAddress) {
					
					List<PersonAndMedicalInfo> personByNames = personRepository.findPersonsByNames(person.getFirstName(), person.getLastName());
					persons.add(personByNames.get(0));

				}
				
				homes.add(new Home(address, persons));
			}
		}
		
		
		
		return homes;
	}



}
