package com.safetynet.alerts.repository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;

public class JSONExtracter {
	
	private HashMap<String, Person> persons;
	
	public JSONExtracter() throws IOException{
		persons = getPersons();
	}
	
	
	/**
	 * get the json file and extract the objects
	 * @return the full json file as a json node
	 */
	public JsonNode getFullJson() throws IOException {
		// Creating an Object Mapper
		ObjectMapper mapper = new ObjectMapper();
		// Getting our file's path
		String jsonPath = "./src/main/resources/data.json";
		File jsonFile = new File(jsonPath);
		// Getting all the contents of the json file
		String content = Files.readString(jsonFile.toPath(), StandardCharsets.US_ASCII);
	    JsonFactory factory = mapper.getFactory();
	    JsonParser parser = factory.createParser(content);
	    JsonNode fullJsonFile = mapper.readTree(parser);
		return fullJsonFile;
	}
	
	/**
	 * Gets the persons objects from the json file
	 * @return a hashmap with the person as a value, with
	 * the key formatted like : "firstName.lastName"
	 */
	public HashMap<String, Person> getPersons() throws IOException {
	    JsonNode jsonPersons = getFullJson().get("persons");
	    HashMap<String, Person> listPersons = new HashMap<String,Person>();
	    
	    for (JsonNode person : jsonPersons) {
	    	String firstName = person.get("firstName").toPrettyString().toLowerCase();
	    	String lastName = person.get("lastName").toPrettyString().toLowerCase();
	    	String keyName = firstName.replaceAll("[^a-zA-Z0-9]", "")+"."
	    					+lastName.replaceAll("[^a-zA-Z0-9]", "");
	    	listPersons.put(keyName,
	    			new Person(person.get("firstName").toPrettyString().replaceAll("[^a-zA-Z0-9]", ""),
	    					person.get("lastName").toPrettyString().replaceAll("[^a-zA-Z0-9]", ""),
	    					person.get("address").toPrettyString().replaceAll("\"", ""),
	    					person.get("city").toPrettyString().replaceAll("[^a-zA-Z0-9]", ""),
	    					person.get("zip").toPrettyString().replaceAll("[^a-zA-Z0-9]", ""),
	    					person.get("phone").toPrettyString().replaceAll("[^a-zA-Z0-9]", ""),
	    					person.get("email").toPrettyString().replaceAll("[^a-zA-Z0-9]", "")
	    					));
        }
		return listPersons;
	}
	
	/**
	 * Gets the firestations objects from the json file
	 * @return a hashmap with the firestations as a value and 
	 * the station number as a key
	 */
	public HashMap<Integer, Firestation> getFirestations() throws IOException {
	    JsonNode jsonFirestations = getFullJson().get("firestations");
	    HashMap<Integer, Firestation> firestations = new HashMap<Integer, Firestation>();
	    
	    for(JsonNode firestation : jsonFirestations) {
	    	int stationNb = Integer.parseInt(firestation.get("station").toPrettyString().replaceAll("[^a-zA-Z0-9]", ""));
	    	firestations.put(stationNb, new Firestation(firestation.get("address").toPrettyString().replaceAll("\"", ""),
	    			stationNb));
	    }
	    
		return firestations;
	}
	
	public HashMap<String, MedicalRecord> getMedicalRecords() throws IOException {
	    JsonNode jsonMedicalRecords = getFullJson().get("medicalrecords");
	    
	    HashMap<String, MedicalRecord> medicalRecords = new HashMap<String, MedicalRecord>();
	    
	    for(JsonNode medicalRecord : jsonMedicalRecords) {
	    	String firstName = medicalRecord.get("firstName").toPrettyString().toLowerCase();
	    	String lastName = medicalRecord.get("lastName").toPrettyString().toLowerCase();
	    	String keyName = firstName.replaceAll("[^a-zA-Z0-9]", "")+"."
	    					+lastName.replaceAll("[^a-zA-Z0-9]", "");
	    	Person newPerson = persons.get(keyName);
	    	List<String> medications = new ArrayList<String>();
	    	List<String> allergies = new ArrayList<String>();
	    	
	    	for(int i=0;i<medicalRecord.get("medications").size();i++) {
	    		medications.add(medicalRecord.get("medications").get(i).toPrettyString().toLowerCase().replaceAll("\"", ""));
	    	}
	    	for(int i=0;i<medicalRecord.get("allergies").size();i++) {
	    		allergies.add(medicalRecord.get("allergies").get(i).toPrettyString().toLowerCase().replaceAll("\"", ""));
	    	}
	    	MedicalRecord mR = new MedicalRecord(newPerson, medicalRecord.get("birthdate").toPrettyString().toLowerCase().replaceAll("\"", ""),
	    											medications, allergies);
	    	medicalRecords.put(keyName, mR);
	    	
	    }
		return medicalRecords;
	}

}
